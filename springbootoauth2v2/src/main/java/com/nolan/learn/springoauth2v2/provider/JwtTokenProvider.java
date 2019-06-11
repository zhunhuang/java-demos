package com.nolan.learn.springoauth2v2.provider;

import com.nolan.learn.springoauth2v2.model.MyUserModel;
import com.nolan.learn.springoauth2v2.properties.AuthenticationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {


    @Autowired
    private AuthenticationProperties authenticationProperties;

    /**
     * Generate token for user login.
     *
     * @param authentication
     * @return return a token string.
     */

    public String createJwtToken(Authentication authentication) {

        //user name

        String username = ((MyUserModel) authentication.getPrincipal()).getUsername();

        //expire time

        Date expireTime = new Date(System.currentTimeMillis() + authenticationProperties.getTokenExpiredMs());

        //create token

        String token = Jwts.builder()

                .setSubject(username)

                .setExpiration(expireTime)

                .setIssuedAt(new Date())

                .signWith(SignatureAlgorithm.HS512, authenticationProperties.getJwtTokenSecret())

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
                    .setSigningKey(authenticationProperties.getJwtTokenSecret())
                    .parseClaimsJws(token);

            return true;

        } catch (Exception ex) {

            //ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, //IllegalArgumentException

            log.error("validate failed : ", ex);

            return false;

        }

    }

}


