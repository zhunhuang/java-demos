package com.nolan.learn.springsecurityjwt1.api;

import lombok.Data;

/**
 * description:
 *
 * @author zhun.huang 2019-03-31
 */

@Data
public class WxAccessTokenModel {

    private String access_token;

    private long expires_in;

    private int errcode;

    private String errmsg;


}
