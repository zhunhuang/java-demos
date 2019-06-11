package platform.security;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 根据用户名返回用户权限信息
 * @author ke.gong
 * @date 2016-11-15
 */
public class ConfigurableUserService implements UserDetailsService {
    
    private static final String DEFAULT_PASSWORD = "000000";

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        
        Set<String> roles = Sets.newHashSet();
        if ("admin".equals(username)) {
            System.out.println("是系统管理员啦");
            roles.add("ROLE_ADMIN");
        }
        final List<GrantedAuthority> authorities = Lists.newArrayList();
        if (roles != null && roles.size() != 0) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            
            UserDetails details = new UserDetails() {
                private static final long serialVersionUID = 8935067102031315430L;

                @Override
                public boolean isEnabled() {
                    return true;
                }
                
                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }
                
                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }
                
                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }
                
                @Override
                public String getUsername() {
                    return username;
                }
                
                @Override
                public String getPassword() {
                    return DEFAULT_PASSWORD;
                }
                
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return authorities;
                }
            };
            return details;
        } 
        return null;
    }

}
