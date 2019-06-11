package platform.security.util;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author: zhun.huang
 * @create: 2018-04-06 下午6:24
 * @email: nolan.zhun@gmail.com
 * @description: 工具类，
 */
public class AntUrlPathMatcher {
    private boolean requiresLowerCaseUrl;
    private PathMatcher pathMatcher;

    public AntUrlPathMatcher() {
        this(true);

    }

    public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = true;
        this.pathMatcher = new AntPathMatcher();
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public Object compile(String path) {
        if (this.requiresLowerCaseUrl) {
            return path.toLowerCase();
        }
        return path;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {

        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    /**]
     *
     * @param path 配置文件中的路径配置
     * @param url 用户访问的真实url
     * @return 两者是否匹配
     */
    public boolean pathMatchesUrl(Object path, String url) {
        if (("/**".equals(path)) || ("**".equals(path))) {
            return true;
        }

        return this.pathMatcher.match((String) path, url);
    }

    public String getUniversalMatchPattern() {
        return "/**";
    }

    public boolean requiresLowerCaseUrl() {
        return this.requiresLowerCaseUrl;
    }
}
