package com.example.demo.domain.models;

public abstract class SortableElement<T, U extends Comparable<U>> {
    protected final T value;
    protected U sortingKey;

    public SortableElement(T value, U sortingKey) {
        this.value = value;
        this.sortingKey = sortingKey;
    }
}
