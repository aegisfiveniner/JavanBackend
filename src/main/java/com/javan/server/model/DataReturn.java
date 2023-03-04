package com.javan.server.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter

public class DataReturn {

    private List<?> data = new ArrayList<>();
    private String message;
    private Boolean status;

    public void setFalse(String message) {
        this.message = message;
        this.status = false;
    }

    public void setSuccess(List<?> data) {
        this.data = data;
        this.message = "Success";
        this.status = true;
    }

    public void setSuccess() {
        this.message = "Success";
        this.status = true;
    }


}
