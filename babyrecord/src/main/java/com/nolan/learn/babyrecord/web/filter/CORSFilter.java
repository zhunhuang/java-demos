package com.nolan.learn.babyrecord.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "corsFilter")
@Slf4j
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        res.setHeader("Access-Control-Allow-Headers", "X-Request-With,x-forwarded-for,HOST,REFER,content-type");
        chain.doFilter(request, response);
        log.info("访问端口：{}",((HttpServletRequest)request).getRequestURI());
    }

    @Override
    public void destroy() {

    }
}
