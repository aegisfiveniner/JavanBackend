package com.management.user.authentication.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.management.user.authentication.JwtAuthentication;
import com.management.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtAuthenticationImpl implements JwtAuthentication {

    @Override
    public String createToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256("${app.jwt.jwtSecret}");
        return JWT.create()
                .withIssuer("jwt")
                .withClaim("role", user.getUserRole().toString())
                .withClaim("tax-role", user.getTaxRole().toString())
                .withClaim("username", user.getUsername())
                .sign(algorithm);
    }

    @Override
    public Map<String, Claim> claimToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("${app.jwt.jwtSecret}");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("jwt")
                .build();

        return verifier.verify(token).getClaims();
    }

    @Override
    public String claimTokenUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256("${app.jwt.jwtSecret}");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("jwt")
                .build();

        String username = verifier.verify(token).getClaims().get("username").toString();
        return username.substring(1, username.length() - 1);
    }
}
