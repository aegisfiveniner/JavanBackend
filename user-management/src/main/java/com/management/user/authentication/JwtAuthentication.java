package com.management.user.authentication;

import com.auth0.jwt.interfaces.Claim;
import com.management.user.entity.User;

import java.util.Map;

public interface JwtAuthentication {

    String createToken(User user);

    Map<String, Claim> claimToken(String token);

    String claimTokenUsername(String token);
}
