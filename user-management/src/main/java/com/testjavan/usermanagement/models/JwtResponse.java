package com.testjavan.usermanagement.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private Long idUser;
    private String email;
    private String username;
    private String role;

    public JwtResponse(
            String accessToken,
            Long idUser,
            String email,
            String username,
            String role) {
        this.token = accessToken;
        this.idUser = idUser;
        this.email = email;
        this.username = username;
        this.role = role;
    }
}
