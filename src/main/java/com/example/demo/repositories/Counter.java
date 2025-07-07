package com.example.demo.repositories;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Counter {
    @Id
    private Long id = 1L; // Always use the same id for singleton counter
    private int value;

    public Counter() {}

    public Counter(int value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
} 