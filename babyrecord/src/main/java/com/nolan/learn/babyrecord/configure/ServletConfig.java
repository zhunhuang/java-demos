package com.nolan.learn.babyrecord.configure;

import com.nolan.learn.babyrecord.web.filter.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Configuration
public class ServletConfig {

    @Bean
    public FilterRegistrationBean servletRegistrationBean() {
        return new FilterRegistrationBean(new CORSFilter());
    }
}
