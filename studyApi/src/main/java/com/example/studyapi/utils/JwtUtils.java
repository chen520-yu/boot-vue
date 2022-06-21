package com.example.studyapi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;

@EqualsAndHashCode
@Component
@Data
public class JwtUtils {
    private String secrit;
    private long expire;


    public String generateToken(long userId){

        Date nowDate = new Date();

        Date date = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("tpe","JWT")
                .setSubject(userId+" ")
                .setIssuedAt(nowDate)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.ES256,secrit)
                .compact();
    }

    public Claims getClaimsByToken(String token){
        return Jwts.parser()
                .setSigningKey(secrit)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isTokenExpired(Date expiration){
        return expiration.before(new Date());
    }



}
