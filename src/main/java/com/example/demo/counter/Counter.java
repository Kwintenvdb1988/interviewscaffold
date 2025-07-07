package com.example.demo.counter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Counter {
    @Id
    private Long id = 1L; // Always use the same id for singleton counter
    private int count;

    public Counter() {}

    public Counter(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
} 