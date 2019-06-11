package com.nolan.learn.springoauth2v2.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author zhun.huang 2019-03-25
 */
@Data
@Component
@ConfigurationProperties(prefix = "authentication")
public class AuthenticationProperties {

    private String jwtTokenSecret;
    private long tokenExpiredMs;
}
