package com.example.demo.counter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CounterService {
    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional
    public int increment() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter(0));
        counter.setValue(counter.getValue() + 1);
        counterRepository.save(counter);
        return counter.getValue();
    }

    @Transactional
    public int decrement() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter(0));
        counter.setValue(counter.getValue() - 1);
        counterRepository.save(counter);
        return counter.getValue();
    }

    @Transactional(readOnly = true)
    public int getValue() {
        return counterRepository.findById(1L).map(Counter::getValue).orElse(0);
    }
} 