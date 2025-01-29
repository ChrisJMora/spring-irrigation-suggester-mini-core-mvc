package com.example.demo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult {
    private ResponseStatus status;
}

