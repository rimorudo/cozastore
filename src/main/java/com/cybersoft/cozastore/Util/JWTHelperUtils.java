package com.cybersoft.cozastore.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTHelperUtils {
// @Value giup lay key khai bao ben file application.properties
    @Value("${jwt.token.key}")
    String secretKey;
    /**
     * Buoc 1: Tao key
     * Buoc 2: Su dung key moi tao de sinh ra token
     */
    public String generateToken(String data){
        //lay key da tao truoc do su dung
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        //Dung key de tao ra token
        String token = Jwts.builder().setSubject(data).signWith(key).compact();
        return token;
    }
    // chuan bi chia khoa de tien hanh giai ma
    public String validToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return  Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token) // Truyen token can giai ma
                .getBody().getSubject();
    }
}
