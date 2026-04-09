package com.codewithjona.sistemalibros.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "mimasdiandf-sdgfsdfjgdfgfd-kbopdfbspdfbd-fgdfgfsgddfgdfjhkhg";

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("email",user.getEmail())
                .claim("role",user.getRoles().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1hora
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
