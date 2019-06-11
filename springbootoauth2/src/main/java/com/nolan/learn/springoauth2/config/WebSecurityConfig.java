package com.nolan.learn.springoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description:
 *
 * @author zhun.huang 2019-03-22
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 对密码进行加密的服务，token化的意思。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //表单登录
                .formLogin()
                //允许访问
                .and().authorizeRequests().antMatchers("/home","/oauth/**").permitAll().anyRequest().authenticated()
                //禁用跨站伪造
                .and().csrf().disable();
    }
}
