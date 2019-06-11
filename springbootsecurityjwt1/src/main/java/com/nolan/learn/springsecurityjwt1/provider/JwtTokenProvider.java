package com.nolan.learn.springsecurityjwt1.provider;

import com.nolan.learn.springsecurityjwt1.model.MyUserDetail;
import com.nolan.learn.springsecurityjwt1.proterties.MySecurityProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@Slf4j
@Component
public class JwtTokenProvider {

    @Autowired
    private MySecurityProperties mySecurityProperties;

    public String createJwtToken(Authentication authentication) {

        String userName = ((MyUserDetail)authentication.getPrincipal()).getUsername();

        Date expireAt = new Date();
        expireAt.setTime(System.currentTimeMillis()+ mySecurityProperties.getTokenExpiredMs());

        String token = Jwts.builder()
                .setExpiration(expireAt)
                .setSubject(userName)
                .signWith(SignatureAlgorithm.HS256, mySecurityProperties.getJwtTokenSecret())
                .compact();

        return token;
    }

    /**
     * validate token eligible.
     * <p>
     * if Jwts can parse the token string and no throw any exception, then the token is eligible.
     *
     * @param token a jws string.
     */

    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(mySecurityProperties.getJwtTokenSecret())
                    .parseClaimsJws(token);

            return true;

        } catch (Exception ex) {

            //ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, //IllegalArgumentException

            System.err.println("validate failed : "+ ex);

            return false;
        }

    }

    /**
     * Get user name from Jwt, the user name have set to jwt when generate token.
     *
     * @param token jwt token.
     * @return user name.
     */
    public String getUsernameFromJwt(String token) {
        return Jwts.parser().setSigningKey(mySecurityProperties.getJwtTokenSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
