package com.example.demo.model.httpResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class ErrorDetail extends Error {
    private Map<String, String> details;
    
    public ErrorDetail(String message) {
        super(message);
    }
    
    public ErrorDetail(String message, Map<String, String> details) {
        super(message);
        this.details = details;
    }
}

