package com.nolan.learn.springsecurityjwt1.proterties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@Component
@ConfigurationProperties("authentication")
@Data
public class MySecurityProperties {

    private String jwtTokenSecret;
    private long tokenExpiredMs;
    private String appId;
    private String appSecret;

    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public long getTokenExpiredMs() {
        return tokenExpiredMs;
    }

    public void setTokenExpiredMs(long tokenExpiredMs) {
        this.tokenExpiredMs = tokenExpiredMs;
    }
}
