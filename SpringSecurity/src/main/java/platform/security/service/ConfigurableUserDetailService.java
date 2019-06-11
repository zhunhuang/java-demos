package platform.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: zhun.huang
 * @create: 2018-04-06 下午4:43
 * @email: nolan.zhun@gmail.com
 * @description: 根据用户名获取用户所有信息
 */
public class ConfigurableUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();

        GrantedAuthority auth2=new SimpleGrantedAuthority("ROLE_ADMIN");
        GrantedAuthority auth1=new SimpleGrantedAuthority("ROLE_USER");

        if("admin".equals(username)){
            auths=new ArrayList<GrantedAuthority>();
            auths.add(auth1);
            auths.add(auth2);
        }
        // admin 账户 密码 123456, 可用, 账号未过期未锁定, 授权未过期, 拥有ROLE_ADMIN和ROLE_USER权限
        User user = new User(username, "123456", true, true, true, true, auths);
        return user;
    }
}
