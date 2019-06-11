package com.nolan.learn.springsecurityjwt1.api;

import lombok.Data;

/**
 * description:
 *
 * @author zhun.huang 2019-03-31
 */
@Data
public class WxSessionModel {

    private String session_key;

    private String openid;

}
