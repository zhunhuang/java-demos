package platform.security;

import org.springframework.security.taglibs.authz.JspAuthorizeTag;
import platform.config.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

/**
 * @author: zhun.huang
 * @create: 2018-04-01 下午10:10
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class AuthorizeTag extends JspAuthorizeTag implements Tag {

    @Override
    public int doStartTag() throws JspException {
        // 判断是否开启权限校验
        if (!Constants.AuthorizeSwitch) {
            return Tag.EVAL_BODY_INCLUDE;
        }
        return super.doStartTag();
    }
}
