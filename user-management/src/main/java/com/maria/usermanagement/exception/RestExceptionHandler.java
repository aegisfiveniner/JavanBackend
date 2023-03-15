package com.maria.usermanagement.exception;

import com.maria.usermanagement.base.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        baseResponse.setMessage(HttpStatus.BAD_REQUEST.name());
        baseResponse.setErrorMessages(errorMessage);

        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {

        String errorMessage = ex.getMessage();
        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setStatusCode(HttpStatus.NOT_FOUND);
        baseResponse.setMessage(HttpStatus.NOT_FOUND.name());
        baseResponse.setErrorMessages(errorMessage);

        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }
}
