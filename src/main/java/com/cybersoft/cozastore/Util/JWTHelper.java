package com.cybersoft.cozastore.Util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTHelper {

    public String generateToken(String data){
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String key = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(key);
        return "";
    }
}
