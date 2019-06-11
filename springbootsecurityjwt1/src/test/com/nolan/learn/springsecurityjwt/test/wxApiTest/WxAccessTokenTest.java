package com.nolan.learn.springsecurityjwt.test.wxApiTest;

import com.nolan.learn.springsecurityjwt1.SpringSecurityJwt1;
import com.nolan.learn.springsecurityjwt1.api.WxAccessTokenAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:
 *
 * @author zhun.huang 2019-03-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityJwt1.class)
public class WxAccessTokenTest {

    @Autowired
    private WxAccessTokenAPI wxAccessTokenAPI;

    @Test
    public void contextLoads() throws Exception {

        wxAccessTokenAPI.getAccessToken();
    }
}
