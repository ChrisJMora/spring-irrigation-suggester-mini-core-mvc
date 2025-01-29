package com.example.demo.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Error extends ApiResult {

    private String message;

    public Error(String message) {
        super(ResponseStatus.ERROR);
        this.message = message;
    }
}
