package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Credentials {
    private String identifier;
    private String password;
}
