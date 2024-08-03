package com.nobroker.rentify.controller.dto;

public class ApiErrorResponse {
    private final int status;
    private final String message;


    // Constructor
    public ApiErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

