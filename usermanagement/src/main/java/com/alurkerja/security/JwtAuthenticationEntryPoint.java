package com.alurkerja.security;

import com.alurkerja.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ResponseUtil<?> baseResponse = new ResponseUtil<>();
        baseResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        baseResponse.setMessage(HttpStatus.UNAUTHORIZED.name());
        baseResponse.setErrorMessages("Unauthorized");

        String responseString = objectMapper.writeValueAsString(baseResponse);
        response.addHeader("content-type", "application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(responseString);
    }
}