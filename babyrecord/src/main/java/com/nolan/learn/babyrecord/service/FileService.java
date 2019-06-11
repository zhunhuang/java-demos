package com.nolan.learn.babyrecord.service;

import com.nolan.learn.babyrecord.exceptions.BusinessException;
import com.nolan.learn.babyrecord.configure.FileUploadConfigure;
import com.nolan.learn.babyrecord.dao.FileInfoRepository;
import com.nolan.learn.babyrecord.model.po.FileInfo;
import com.nolan.learn.babyrecord.model.vo.ResponseModel;
import com.nolan.learn.babyrecord.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Slf4j
@Service
public class FileService {

    @Resource
    private FileInfoRepository fileInfoRepository;

    @Resource
    private FileUploadConfigure uploadConfigure;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传结果
     * @throws BusinessException 异常信息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseModel<String> upload(MultipartFile file, HttpServletRequest request) throws BusinessException {
        //基础路径  E:/springboot-upload/image/
        String basePath = uploadConfigure.getBasePath();
        //获取文件保存路径 \20180608\113339\
        String folder = FileUtils.getFolder();
        // 获取前缀为"FL_" 长度为20 的文件名  FL_eUljOejPseMeDg86h.png
        String fileName = FileUtils.getFileName() + FileUtils.getFileNameSub(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // E:\springboot-upload\image\20180608\113339
            Path filePath = Files.createDirectories(Paths.get(basePath, folder));
            log.info("path01-->{}", filePath);

            //写入文件  E:\springboot-upload\image\20180608\113339\FL_eUljOejPseMeDg86h.png
            Path fullPath = Paths.get(basePath, folder, fileName);
            log.info("fullPath-->{}", fullPath);
            // E:\springboot-upload\image\20180608\113339\FL_eUljOejPseMeDg86h.png
            Files.write(fullPath, file.getBytes(), StandardOpenOption.CREATE);
            String fileUrl = "http://192.168.1.103:8081/api/file/view?fileName=" + URLEncoder.encode(fullPath.toString(),"utf-8");
            //保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setResourceId(String.valueOf(System.currentTimeMillis()));
            fileInfo.setValid(true);
            fileInfo.setFileOriginalName(file.getOriginalFilename());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileMd5(FileUtils.md5File(fullPath.toString()));

            fileInfo.setFileName(fileName);
            fileInfo.setFileUrl(fullPath.toString());
            fileInfo.setFilePath(filePath.toString());

            fileInfoRepository.save(fileInfo);
            return ResponseModel.success(fileUrl);
        } catch (Exception e) {
            Path fullPath = Paths.get(basePath, folder, fileName);
            log.error("写入文件异常,删除文件。。。。", e);
            try {
                org.apache.tomcat.util.http.fileupload.FileUtils.forceDeleteOnExit(fullPath.toFile());
            } catch (IOException e1) {
                log.error("写入文件异常,删除文件失败。。。。", e);
            }
            return ResponseModel.fail(-1, e.getMessage());
        }
    }


    /**
     * 文件下载
     *
     * @param fileName
     * @param res
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     */
    public void downloadFile(String fileName, HttpServletResponse res) throws BusinessException, UnsupportedEncodingException {
        if (fileName == null) {
            throw new BusinessException(1001, "文件名不能为空");
        }

        // 通过文件名查找文件信息
        FileInfo fileInfo = fileInfoRepository.findByFileName(fileName);
        log.info("fileInfo-->{}", fileInfo);
        if (fileInfo == null) {
            throw new BusinessException(2001, "文件名不存在");
        }

        //设置响应头
        res.setContentType("application/force-download");// 设置强制下载不打开
        res.addHeader("Content-Disposition", "attachment;fileName=" +
                new String(fileInfo.getFileOriginalName().getBytes(StandardCharsets.UTF_8), "iso8859-1"));// 设置文件名
        res.setHeader("Context-Type", "application/xmsdownload");

        //判断文件是否存在
        File file = new File(Paths.get(fileInfo.getFilePath(), fileName).toString());
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = res.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                log.info("下载成功");
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(9999, e.getMessage());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 文件查看
     */
    public FileInfo getImage(String fileName) throws IOException {
        log.info("fileName-->{}", fileName);
        FileInfo fileInfo = fileInfoRepository.findByFileUrlAndValid(fileName, true);
        if (fileInfo == null) {
            return null;
        }
        Path path = Paths.get(fileInfo.getFilePath(), fileInfo.getFileName());
        log.info("path-->{}", path);
        fileInfo.setContent(Files.newInputStream(path));
        return fileInfo;
    }

    /**
     * 根据资源id查询文件信息
     *
     * @param resourceId
     * @return
     * @throws BusinessException
     */
    public ResponseModel<?> findFileList(String resourceId) throws BusinessException {
        if (resourceId == null) {
            throw new BusinessException(1001, "资源id不能为空");
        }
        // 根据资源id查询文件信息
        return ResponseModel.success(fileInfoRepository.findByResourceId(resourceId));
    }


    /**
     * 逻辑删除文件
     *
     * @param fileName
     * @return
     * @throws BusinessException
     */
    public ResponseModel<?> deleteFile(String fileName) throws BusinessException {
        if (fileName == null) {
            throw new BusinessException(1001, "文件名不能为空");
        }
        FileInfo fileInfo = fileInfoRepository.findByFileName(fileName);
        if (fileInfo == null) {
            throw new BusinessException(2001, "文件名:" + fileName + " 不存在");
        }
        // 逻辑删除文件
        fileInfo.setValid(false);
        return ResponseModel.success(fileInfo);
    }
}
