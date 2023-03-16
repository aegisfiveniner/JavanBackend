package com.javanbackend.usermanagement.configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.javanbackend.usermanagement.service.IUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${usermanagement.app.jwtSecret}")
    private String jwtSecret;
    
    @Value("${usermanagement.app.jwtExpiration}")
    private long jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
        IUserDetails userPrincipal = (IUserDetails) authentication.getPrincipal();
        return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            LOGGER.error("Validate Token Error: {}", e.getMessage());
        }
        
        return false;
    }
}
