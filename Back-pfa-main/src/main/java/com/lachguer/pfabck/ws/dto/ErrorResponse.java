package com.lachguer.pfabck.ws.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private long timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}