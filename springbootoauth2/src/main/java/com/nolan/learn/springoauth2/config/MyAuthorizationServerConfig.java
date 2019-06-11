package com.nolan.learn.springoauth2.config;

import com.nolan.learn.springoauth2.model.UserModel;
import com.nolan.learn.springoauth2.properties.ClientLoadProperties;
import com.nolan.learn.springoauth2.properties.ClientProperties;
import com.nolan.learn.springoauth2.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * 认证服务器
 * @author zhun.huang 2019-03-22
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 看看这个在哪里注册的吧
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;
    @Resource
    private ClientLoadProperties clientLoadProperties;

    /**
     * spring-boot-starter-data-redis 提供的功能，
     * 只需要在application.yml中按照规范定义redis连接参数即可
     */
    @Autowired
    private RedisConnectionFactory connectionFactory;


    /**
     * 定义token的存储方式,
     * 只要注册了这个依赖，则框架自动使用该tokenStore进行token存储服务
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    /**
     * 定义令牌端点上的安全性约束
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
     * 用于定义第三方客户端详细信息服务的配置程序。可以初始化客户端详细信息，也可以只引用现有存储。clients.jdbc();
     *
     * @param clients a configurer that defines the client details service. Client details can be initialized, or you can just refer to an existing store.
     * @throws Exception exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (clientLoadProperties.getClients() != null &&
                clientLoadProperties.getClients().length > 0) {
            for (ClientProperties config : clientLoadProperties.getClients()) {
                builder
                        //设置客户端和密码
                        .withClient(config.getClientId()).secret(config.getClientSecret())
                        //设置token有效期
                        .accessTokenValiditySeconds(7 * 24 * 3600)
                        //设置refreshToken有效期
                        .refreshTokenValiditySeconds(7 * 24 * 3600)
                        //支持的认证方式
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password").autoApprove(false)
                        //授权域
                        .scopes("app","write");
            }
        }
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
                .tokenStore(tokenStore());
    }

    /**
     * 定义jwt的生成方式。这里自定义了把 uin存储到了 accesstoken中。
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                final Map<String, Object> additionalInformation = new HashMap<>();
                UserModel userModel = (UserModel) authentication.getUserAuthentication().getPrincipal();
                //把用户的主键uin放进去
                additionalInformation.put("uin", userModel.getUin());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);
            }
        };
        //对称加密
        converter.setSigningKey("123");
        return converter;
    }
}
