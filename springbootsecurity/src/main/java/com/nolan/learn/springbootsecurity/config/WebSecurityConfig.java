package com.nolan.learn.springbootsecurity.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

/**
 * description:
 * 一个继承了WebSecurityConfigurerAdapter的带有@Configuration注解的配置类， 就代表了一个filter Chain
 * 如果不设置@Order注解，则默认顺序是-100，优先级超过默认的filter chain很高。
 * 这里设置的Order，比默认的小1，所以优先级也超过默认的。
 * @author zhun.huang 2019-03-22
 */
@Configuration
@Order(value = SecurityProperties.BASIC_AUTH_ORDER-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                该filter chain，匹配所有的"/**"请求，返回的还是HttpSecurity对象。
                .antMatcher("/**")
                //设置表单登录
                .formLogin()
                // 设置表单登录的页面,默认也是这个，方法是GET
//                .loginPage("/login")
                // 设置处理表单提交请求处理端点， 默认也是这个，只是方法是POST
                .loginProcessingUrl("/login")
                // 登录失败时的跳转页面, 默认是默认是使用SimpleUrlAuthenticationFailureHandler实现
                .failureForwardUrl("/error")
                // 登录成功时的跳转页面，默认是从哪里跳转过来，就从哪里跳转回去。
                // 这里会使用ForwardAuthenticationSuccessHandler 的实现，设置都跳转到home端点。
                // 而默认实现是使用SavedRequestAwareAuthenticationSuccessHandler， 其会保存跳转到登录页面的url，登录成功之后再forward回去。这里通过添加请求参数redirectUrl实现。或者通过redis缓存跳转链接实现？
                .successForwardUrl("/home")
                // 设置了上面的successForwardUrl就已经指定了处理handler了，这里再指定一遍跟上面的效果是一样的
                .successHandler(new ForwardAuthenticationSuccessHandler("/home"))
                // 登录失败后，会forward 到失败页面。默认是使用SimpleUrlAuthenticationFailureHandler实现。
                .failureHandler(new ForwardAuthenticationFailureHandler("/error?error"))
                .and()
                .authorizeRequests()
                .antMatchers("/home","/login").permitAll()
                .anyRequest().authenticated()
                //禁用跨站伪造
                .and().csrf().disable();
    }
}
