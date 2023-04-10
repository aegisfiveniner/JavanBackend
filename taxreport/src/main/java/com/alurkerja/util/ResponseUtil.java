package com.alurkerja.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;

@Getter
@Setter
public class ResponseUtil<T> {

    private HttpStatus statusCode;
    private String message;
    private String errorMessages;
    private T data;

    public ResponseUtil() {

    }

    public ResponseUtil(HttpStatus statusCode, String message, String errorMessages, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorMessages = errorMessages;
        this.data = data;
    }

    public ResponseUtil(String errorMessages) {
        this.errorMessages = errorMessages;
        this.data = null;
    }

    public ResponseUtil(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseUtil(HttpStatus statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}