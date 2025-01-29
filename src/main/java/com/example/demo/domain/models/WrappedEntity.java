package com.example.demo.domain.models;

import lombok.Getter;

@Getter
public class WrappedEntity<T> extends ApiResult {
    private T data;

    public WrappedEntity(T data) {
        super(ResponseStatus.SUCCESS);
        this.data = data;
    }
}
