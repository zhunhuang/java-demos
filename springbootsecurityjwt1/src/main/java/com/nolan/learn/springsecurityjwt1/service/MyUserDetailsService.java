package com.nolan.learn.springsecurityjwt1.service;

//import com.alibaba.druid.util.StringUtils;
//import com.nolan.learn.springsecurityjwt1.dao.UserDao;
import com.nolan.learn.springsecurityjwt1.model.DO.UserDO;
import com.nolan.learn.springsecurityjwt1.model.MyUserDetail;
import com.nolan.learn.springsecurityjwt1.provider.WXAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
//    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("用户openId为："+username);
        System.err.println("模拟从数据库拿到用户信息");
        if (username.equals("openID6666")){
            UserDO userDO= new UserDO();
            userDO.setOpenId("openID6666");
            userDO.setCreateTime(new Date());
            userDO.setRoles("ROLE_USER");
            userDO.setNickName("已经保存用户");
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            MyUserDetail myUserDetail = new MyUserDetail();
            myUserDetail.setPassword("");
            myUserDetail.setAccountNotExpired(true);
            myUserDetail.setRoles(grantedAuthorities);
            myUserDetail.setUsername(userDO.getOpenId());
            return myUserDetail;
        } else if (username.equals("openID7777")){
            System.err.println("当前登录用户openId为：openID7777");
            Random random = new Random();
            UserDO userDO= new UserDO();
            userDO.setOpenId("openID7777");
            userDO.setCreateTime(new Date());
            userDO.setNickName("微信昵称openID7777");
            userDO.setRoles("ROLE_USER");
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(userDO.getRoles()));
            MyUserDetail myUserDetail = new MyUserDetail();
            myUserDetail.setPassword("");
            myUserDetail.setAccountNotExpired(true);
            myUserDetail.setUsername(userDO.getOpenId());
            myUserDetail.setRoles(grantedAuthorities);
            return myUserDetail;
        } else {
            throw new BadCredentialsException("用户未找到");
        }
//        if (StringUtils.isEmpty(username)) {
//            throw new UsernameNotFoundException("user not found");
//        }
//        UserDO userDO = userDao.findByOpenId(username);
//        if (userDO == null) {
//            throw new UsernameNotFoundException("用户未授权");
//        }
//        MyUserDetail myUserDetail = new MyUserDetail();
//        myUserDetail.setUsername(userDO.getOpenId());
//        myUserDetail.setPassword("");
//        myUserDetail.setAccountNotExpired(true);
//        myUserDetail.setRoles(AuthorityUtils.commaSeparatedStringToAuthorityList(userDO.getRoles()));
//        return myUserDetail;
    }
}
