package com.nolan.learn.babyrecord.web.controller;

import com.nolan.learn.babyrecord.model.vo.*;
import com.nolan.learn.babyrecord.service.RecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@RestController
@RequestMapping("api/record")
public class RecordInfoResource {

    @Resource
    private RecordService recordService;

    @RequestMapping("query")
    public ResponseModel<List<RecordInfoExtendVO>> query(RecordInfoRequest request) {
        try {
            return ResponseModel.success(recordService.query(request));
        } catch (Exception e) {
            return ResponseModel.fail(-1,e.getMessage());
        }
    }

    @PostMapping("add")
    public ResponseModel<Boolean> add(@RequestBody AddRecordRequest request) {
        try {
            return ResponseModel.success(recordService.add(request));
        } catch (Exception e) {
            return ResponseModel.fail(-1,e.getMessage());
        }
    }

    @RequestMapping("delete")
    public ResponseModel<Boolean> delete(String userId, Long id) {
        try {
            return ResponseModel.success(recordService.delete(userId,id));
        } catch (Exception e) {
            return ResponseModel.fail(-1,e.getMessage());
        }
    }
}
