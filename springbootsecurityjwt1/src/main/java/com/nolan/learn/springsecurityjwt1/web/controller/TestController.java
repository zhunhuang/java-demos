package com.nolan.learn.springsecurityjwt1.web.controller;

import com.nolan.learn.springsecurityjwt1.api.WxAccessTokenAPI;
import com.nolan.learn.springsecurityjwt1.api.WxAccessTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author zhun.huang 2019-03-31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WxAccessTokenAPI wxAccessTokenAPI;

    @RequestMapping("/accessToken")
    public WxAccessTokenModel getAccessToken() {
        return wxAccessTokenAPI.getAccessToken();
    }
}
