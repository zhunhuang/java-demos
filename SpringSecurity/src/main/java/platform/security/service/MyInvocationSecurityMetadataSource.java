package platform.security.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import platform.security.util.AntUrlPathMatcher;

import java.util.*;

/**
 * @author: zhun.huang
 * @create: 2018-04-06 下午5:21
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntUrlPathMatcher pathMatcher = new AntUrlPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     *     tomcat启动时实例化一次
     */
    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    /**
     *     tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
     */
    private void loadResourceDefine() {
        resourceMap = new HashMap<>();
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        atts.add(ca);
        resourceMap.put("/index.jsp", atts);
        Collection<ConfigAttribute> attsno =new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig("ROLE_NO");
        attsno.add(cano);
        resourceMap.put("/other.jsp", attsno);
    }

    /**
     *     参数是要访问的url，返回这个url对于的所有权限（或角色）
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 将参数转为url
        String url = ((FilterInvocation)object).getRequestUrl();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (pathMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?>clazz) {
        return true;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
