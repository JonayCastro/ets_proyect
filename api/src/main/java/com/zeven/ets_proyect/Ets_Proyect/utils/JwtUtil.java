package com.zeven.ets_proyect.Ets_Proyect.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(Long userId) {
        int expirationLimit = 1000 * 60 * 60 * 10; // 10 horas
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationLimit))
                .signWith(Keys.hmacShaKeyFor(this.secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Long extractUserId(String token) {
        String subject = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(this.secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Long.parseLong(subject);
    }

    public boolean validateToken(String token, Long userId) {
        return userId.equals(extractUserId(token));
    }
}
