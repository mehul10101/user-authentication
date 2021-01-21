package com.project.userAuthentication.utils;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
