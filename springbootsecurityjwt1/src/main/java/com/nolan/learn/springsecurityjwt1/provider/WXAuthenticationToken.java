package com.nolan.learn.springsecurityjwt1.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * description:
 *
 * @author zhun.huang 2019-03-27
 */
public class WXAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    private String code;

    public WXAuthenticationToken(Object principal, Object credentials, String code) {
        super((Collection)null);
        this.principal = principal;
        this.credentials = credentials;
        this.code = code;
        this.setAuthenticated(false);
    }

    public WXAuthenticationToken(Object principal, Object credentials, String code, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.code = code;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
