package com.vidupload.configjwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private static final SecretKey secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long ExpirationTime = 86400000;//1 day in milliseconds

    public static String generateToken(Authentication authentication){
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ExpirationTime))
                .signWith(secretkey)
                .compact();
    }

}
