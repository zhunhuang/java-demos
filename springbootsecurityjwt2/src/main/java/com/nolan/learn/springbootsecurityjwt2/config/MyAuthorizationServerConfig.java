package com.nolan.learn.springbootsecurityjwt2.config;

import com.nolan.learn.springbootsecurityjwt2.model.MyUserDetail;
import com.nolan.learn.springbootsecurityjwt2.proterties.MySecurityProperties;
import com.nolan.learn.springbootsecurityjwt2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lvhaibao
 * @description 认证服务器
 * @date 2018/12/25 0025 10:39
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private MySecurityProperties mySecurityProperties;

//    /**
//     * 定义token的存储方式
//     *
//     * @return TokenStore
//     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }

    /**
     * 定义令牌端点上的安全性约 束
     *
     * @param oauthServer oauthServer defines the security constraints on the token endpoint.
     * @throws Exception exception
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        oauthServer.allowFormAuthenticationForClients();

    }

    /**
     * 定义授权和令牌端点以及令牌服务
     *
     * @param endpoints defines the authorization and token endpoints and the token services.
     * @throws Exception exception
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //指定认证管理器
                .authenticationManager(authenticationManager)
                //用户账号密码认证
                .userDetailsService(userDetailsService)
                // refresh_token
                .reuseRefreshTokens(false)
                //指定token存储位置
//                .tokenStore(tokenStore())
                // 配置JwtAccessToken转换器
                .accessTokenConverter(accessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                final Map<String, Object> additionalInformation = new HashMap<>();
                MyUserDetail userModel = (MyUserDetail) authentication.getUserAuthentication().getPrincipal();
                //把用户的主键uin放进去
                additionalInformation.put("username", userModel.getUsername());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);
            }
        };
        converter.setSigningKey(mySecurityProperties.getJwtTokenSecret());
        return converter;
    }

}
