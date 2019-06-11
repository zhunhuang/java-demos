package com.nolan.learn.babyrecord.web.controller;

import com.nolan.learn.babyrecord.model.vo.AddBabyRequest;
import com.nolan.learn.babyrecord.model.vo.ResponseModel;
import com.nolan.learn.babyrecord.model.vo.UserInfo;
import com.nolan.learn.babyrecord.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Controller
@RequestMapping("/api/user")
public class UserInfoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoResource.class);

    @Resource
    private UserService userService;

    @RequestMapping("info")
    @ResponseBody
    public ResponseModel<UserInfo> home(String userId) {
        try {
            UserInfo userInfo = userService.homePage(userId);
            return ResponseModel.success(userInfo);
        } catch (Exception e) {
            LOGGER.error("home response error, id:{}, cause:", userId, e);
            return ResponseModel.fail(-1, e.getMessage());
        }
    }

    @PostMapping("addBaby")
    @ResponseBody
    public ResponseModel<Boolean> addBaby(AddBabyRequest request) {
        try {
            Boolean success = userService.addBaby(request);
            return ResponseModel.success(success);
        } catch (Exception e) {
            LOGGER.error("addBaby response error, request:{}, cause:", request, e);
            return ResponseModel.fail(-1, e.getMessage());
        }
    }
}
