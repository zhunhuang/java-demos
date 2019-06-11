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
public class WxCode2SessionAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxCode2SessionAPI.class);

    private String wxSessionApiEndPoint = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&grant_type=authorization_code&js_code=";

    public WxCode2SessionAPI(String appId, String appSecret) {
        wxSessionApiEndPoint = String.format(wxSessionApiEndPoint,appId,appSecret);
    }

    public WxSessionModel getSession(String code) {
        String url = wxSessionApiEndPoint.concat(code);

        String s = HttpUtil.get(url, null, null);

        WxSessionModel wxSessionModel = JacksonUtil.deSerialize(s, WxSessionModel.class);

        LOGGER.info("getAccessToken from wx, response :{}", s);

        return wxSessionModel;
    }

}
