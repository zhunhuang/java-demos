package com.nolan.learn.springsecurityjwt1.provider;

import com.nolan.learn.springsecurityjwt1.api.WxCode2SessionAPI;
import com.nolan.learn.springsecurityjwt1.api.WxSessionModel;
import com.nolan.learn.springsecurityjwt1.model.DO.UserDO;
import com.nolan.learn.springsecurityjwt1.model.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private WxCode2SessionAPI wxCode2SessionAPI;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        if (!(auth instanceof WXAuthenticationToken)) {
            System.err.println("WXAuthenticationToken类型不对");
            return null;
        } else {
            WXAuthenticationToken token = (WXAuthenticationToken)auth;
            if (token.getCode() == null || token.getCode().trim().length()==0) {
                System.err.println("code 为空");
                return auth;
            }
            WxSessionModel session = wxCode2SessionAPI.getSession(token.getCode());
            String openId = session.getOpen_id();
            if (token.getCode().equals("12345")){
                System.err.println("从微信获取到的openId为："+ openId);
                System.err.println("数据库没查到，新用户");
                Random random = new Random();
                int userId = random.nextInt();
                System.err.println("插入数据库");
                UserDO userDO= new UserDO();
                userDO.setOpenId(openId);
                userDO.setCreateTime(new Date());
                userDO.setNickName("微信昵称");
                userDO.setRoles("ROLE_USER");
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                MyUserDetail myUserDetail = new MyUserDetail();
                myUserDetail.setAccountNotExpired(true);
                myUserDetail.setPassword("");
                myUserDetail.setUsername(userDO.getOpenId());
                myUserDetail.setRoles(grantedAuthorities);
                WXAuthenticationToken newAuth = new WXAuthenticationToken(myUserDetail, "","", grantedAuthorities);
                return newAuth;
            } else {
                System.err.println("从微信获取到的openId为："+ openId);
                System.err.println("数据库查到了，老用户");
                Random random = new Random();
                int userId = random.nextInt();
                System.err.println("更新数据库");
                UserDO userDO= new UserDO();
                userDO.setOpenId(openId);
                userDO.setCreateTime(new Date());
                userDO.setNickName("微信昵称2");
                userDO.setRoles("ROLE_USER");
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(userDO.getRoles()));
                MyUserDetail myUserDetail = new MyUserDetail();
                myUserDetail.setAccountNotExpired(true);
                myUserDetail.setPassword("");
                myUserDetail.setUsername(userDO.getOpenId());
                myUserDetail.setRoles(grantedAuthorities);
                WXAuthenticationToken newAuth = new WXAuthenticationToken(myUserDetail, "","", grantedAuthorities);
                return newAuth;
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WXAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
