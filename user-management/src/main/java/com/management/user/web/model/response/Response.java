package com.management.user.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /**
     * HTTP status
     */
    private Integer status; // Status code (200, 400, 404)
    /**
     * Return Data
     */
    private T data;
    /**
     * Map for errors
     */
    private String message;

    public Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
