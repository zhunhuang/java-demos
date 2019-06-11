package com.nolan.learn.springbootsecurityjwt2.proterties;

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

}
