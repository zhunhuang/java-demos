package com.nolan.learn.babyrecord.web.controller;

import com.nolan.learn.babyrecord.exceptions.BusinessException;
import com.nolan.learn.babyrecord.model.po.FileInfo;
import com.nolan.learn.babyrecord.model.vo.ResponseModel;
import com.nolan.learn.babyrecord.service.FileService;
import com.nolan.learn.babyrecord.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Slf4j
@RestController()
@RequestMapping("api/file")
public class FileResource {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     * 1. 文件上传后的文件名
     * 2. 上传后的文件路径 , 当前年月日时 如:2018060801  2018年6月8日 01时
     * 3. file po 类需要保存文件信息 ,旧名 ,大小 , 上传时间 , 是否删除 ,
     *
     * @param file 文件
     * @return 上传结果
     */
    @PostMapping("uploadFile")
    public ResponseModel<?> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return ResponseModel.fail(-1, "文件不能为空");
        }
        try {
            return fileService.upload(file, request);
        } catch (BusinessException e) {
            return ResponseModel.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            return ResponseModel.fail(-1, e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        try {
            fileService.downloadFile(fileName, res);
        } catch (BusinessException e) {
            log.error("downloadFile error, cause:", e);
        } catch (UnsupportedEncodingException e) {
            log.error("downloadFile UnsupportedEncodingException error, cause:", e);
        }
    }

    /**
     * 文件查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> view(@RequestParam("fileName") String fileName) {
        FileInfo fileInfo = null;
        try {
            fileInfo = fileService.getImage(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders header = new HttpHeaders();
        if (FileUtils.match(fileInfo.getFileName(), "jpg", "png", "gif", "bmp", "tif")) {
            header.setContentType(MediaType.IMAGE_JPEG);
        } else if (FileUtils.match(fileInfo.getFileName(), "html", "htm")) {
            header.setContentType(MediaType.TEXT_HTML);
        } else if (FileUtils.match(fileInfo.getFileName(), "pdf")) {
            header.setContentType(MediaType.APPLICATION_PDF);
        } else {
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        header.add("X-Filename", fileInfo.getFileName());
        header.add("X-MD5", fileInfo.getFileMd5());

        return new ResponseEntity<>(new InputStreamResource(fileInfo.getContent()), header, HttpStatus.OK);
    }

    /**
     * 文件列表查询
     */
    @RequestMapping(value = "/find")
    public ResponseModel<?> findList(@RequestParam("resourceId") String resourceId) {
        try {
            return fileService.findFileList(resourceId);
        } catch (BusinessException e) {
            log.error("findList error, cause:", e);
            return ResponseModel.fail(e.getCode(), e.getMessage());
        }
    }

    /**
     * 逻辑删除文件
     */
    @RequestMapping(value = "/deleteFile")
    public ResponseModel<?> deleteFile(@RequestParam("fileName") String fileName) {
        try {
            return fileService.deleteFile(fileName);
        } catch (BusinessException e) {
            log.error("deleteFile error, cause:", e);
            return ResponseModel.fail(e.getCode(), e.getMessage());
        }
    }
}
