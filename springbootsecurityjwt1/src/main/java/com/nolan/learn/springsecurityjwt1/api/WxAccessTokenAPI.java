package com.nolan.learn.springsecurityjwt1.api;

import com.nolan.learn.springsecurityjwt1.util.HttpUtil;
import com.nolan.learn.springsecurityjwt1.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description:
 *
 * @author zhun.huang 2019-03-31
 */
public class WxAccessTokenAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxAccessTokenAPI.class);

    private String appId;

    private String appSecret;

    private String wxAccessTokenApiEndPoint = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public WxAccessTokenAPI(String appId, String appSecret) {
        wxAccessTokenApiEndPoint = String.format(wxAccessTokenApiEndPoint,appId,appSecret);
    }

    public WxAccessTokenModel getAccessToken() {
        String s = HttpUtil.get(wxAccessTokenApiEndPoint,null,null);

        WxAccessTokenModel wxAccessTokenModel = JacksonUtil.deSerialize(s, WxAccessTokenModel.class);

        LOGGER.info("getAccessToken from wx, response :{}", s);

        return wxAccessTokenModel;

    }
}
