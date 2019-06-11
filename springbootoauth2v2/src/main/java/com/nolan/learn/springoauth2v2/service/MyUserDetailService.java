package com.nolan.learn.springoauth2v2.service;

import com.nolan.learn.springoauth2v2.dao.MyUserDao;
import com.nolan.learn.springoauth2v2.model.MyUserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author zhun.huang 2019-03-25
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}",s);
        if (StringUtils.isEmpty(s)) {
            throw new UsernameNotFoundException("账号密码错误 = " + s);
        }
        MyUserModel userModel = userDao.findByUsername(s);
        if (userModel == null) {
            log.error("帐号未找到 = " + s);
            throw new UsernameNotFoundException("帐号未找到 = " + s);
        }

        //数据库取到的密码，后面返回的是用户用户哪些权限
        String password = passwordEncoder.encode(userModel.getPassword());

        log.info("该用户数据库密码为==" + password);
        return new MyUserModel(userModel.getUid(), userModel.getUsername(), password, userModel.getMobile(),userModel.getRoles());
    }
}
