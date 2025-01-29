package com.example.demo.domain.ports;

public abstract class Builder<T> {

    protected T instance;

    public abstract Builder<T> reset();

    public abstract T build();
}
