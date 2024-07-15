package com.vidupload.configjwt;

import com.vidupload.entity.User;
import com.vidupload.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private static final SecretKey secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long ExpirationTime = 86400000;//1 day in milliseconds
    @Autowired
    private  UserRepository userRepository;
    public  String generateToken(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal() ;
        User user = userRepository.findByEmail(userPrincipal.getUsername());
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("email",user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ExpirationTime))
                .signWith(secretkey)
                .compact();
    }

}
