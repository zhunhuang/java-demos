package com.nolan.learn.springsecurityjwt1.web.filter;

import com.nolan.learn.springsecurityjwt1.provider.WXAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description:
 *
 * @author zhun.huang 2019-03-27
 */
public class WxLoginFilter extends AbstractAuthenticationProcessingFilter {

    public WxLoginFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler authenticationFailureHandler) {
        super(new AntPathRequestMatcher("/wxLogin"));
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/wxLogin"));
        setAllowSessionCreation(false);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String code = obtainCode(request);
        if (code == null || code.trim().length()==0) {
            response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"parameter 'code' required");
        }
        super.doFilter(req, res, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String code = obtainCode(request);

        if (code == null) {
            code = "";
        }


        WXAuthenticationToken authRequest = new WXAuthenticationToken(
                null, "",code);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter("code");
    }

    protected void setDetails(HttpServletRequest request,
                              WXAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
