package com.nolan.learn.springsecurityjwt1.config;

import com.nolan.learn.springsecurityjwt1.api.WxAccessTokenAPI;
import com.nolan.learn.springsecurityjwt1.api.WxCode2SessionAPI;
import com.nolan.learn.springsecurityjwt1.proterties.MySecurityProperties;
import com.nolan.learn.springsecurityjwt1.provider.JwtTokenProvider;
import com.nolan.learn.springsecurityjwt1.provider.MyAuthenticationProvider;
import com.nolan.learn.springsecurityjwt1.service.MyUserDetailsService;
import com.nolan.learn.springsecurityjwt1.web.filter.JwtAuthenticationFilter;
import com.nolan.learn.springsecurityjwt1.web.filter.WxLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * description:
 * spring 的配置，基本上都是继承一个adapter，然后重写默认配置
 * @author zhun.huang 2019-03-26
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private MySecurityProperties mySecurityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public WxAccessTokenAPI wxAccessTokenAPI(MySecurityProperties mySecurityProperties) {
        return new WxAccessTokenAPI(mySecurityProperties.getAppId(),mySecurityProperties.getAppSecret());
    }

    @Bean
    public WxCode2SessionAPI wxCode2SessionAPI(MySecurityProperties mySecurityProperties) {
        return new WxCode2SessionAPI(mySecurityProperties.getAppId(),mySecurityProperties.getAppSecret());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return myUserDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider,myUserDetailService),UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new WxLoginFilter(authenticationManager,authenticationSuccessHandler,authenticationFailureHandler),UsernamePasswordAuthenticationFilter.class)
//                因为我们使用了token，所以session要禁止掉创建和使用，不然会白白耗掉很多空间，SessionCreationPolicy设为STATELESS，即永不创建HttpSession并且不会使用HttpSession去获取SecurityContext。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
//               TODO, RSA加密传输登陆的账号密码
                .authenticationProvider(myAuthenticationProvider)
//                .formLogin()
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler).and()
                .authorizeRequests().antMatchers("/wxLogin","/home","/test/**").permitAll()
                .anyRequest().authenticated();
    }
}
