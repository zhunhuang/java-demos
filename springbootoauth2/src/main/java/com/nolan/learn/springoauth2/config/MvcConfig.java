package com.nolan.learn.springoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * description:
 * 继承 WebMvcConfigurerAdapter  可以用来个性化mvc配置，只需要重写WebMvcConfigurerAdapter中的方法即可
 * 不重写，即走默认配置。
 * @author zhun.huang 2019-03-25
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 这里需要引入视图引擎才能让这个配置生效
     * 这里引入的是thymeleaf， 只需要引入spring-boot-starter-thymeleaf 的依赖，无需配置即可完成。
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("myHome");
        registry.addViewController("/").setViewName("myHome");
    }

}
