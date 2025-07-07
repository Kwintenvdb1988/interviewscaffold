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
        counter.setCount(counter.getCount() + 1);
        counterRepository.save(counter);
        return counter.getCount();
    }

    @Transactional
    public int decrement() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter(0));
        counter.setCount(counter.getCount() - 1);
        counterRepository.save(counter);
        return counter.getCount();
    }

    @Transactional(readOnly = true)
    public int getCount() {
        return counterRepository.findById(1L).map(Counter::getCount).orElse(0);
    }
} 