package com.nolan.learn.springbootsecurityjwt2.service;

import com.alibaba.druid.util.StringUtils;
import com.nolan.learn.springbootsecurityjwt2.dao.UserDao;
import com.nolan.learn.springbootsecurityjwt2.model.DO.UserDO;
import com.nolan.learn.springbootsecurityjwt2.model.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("user not found");
        }
        UserDO userDO = userDao.findByOpenId(username);
        if (userDO == null) {
            throw new UsernameNotFoundException("用户未授权");
        }
        MyUserDetail myUserDetail = new MyUserDetail();
        myUserDetail.setUsername(userDO.getOpenId());
        myUserDetail.setPassword("");
        myUserDetail.setAccountNotExpired(true);
        myUserDetail.setRoles(AuthorityUtils.commaSeparatedStringToAuthorityList(userDO.getRoles()));
        return myUserDetail;
    }
}
