package com.example.demo.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CounterService.class)
class CounterServiceTest {
    @MockBean
    private CounterRepository counterRepository;

    private CounterService counterService;

    @BeforeEach
    void setUp() {
        counterService = new CounterService(counterRepository);
    }

    @Test
    void increment_shouldIncreaseCounter() {
        Counter counter = new Counter(0);
        when(counterRepository.findById(1L)).thenReturn(java.util.Optional.of(counter));
        when(counterRepository.save(any(Counter.class))).thenAnswer(i -> i.getArgument(0));
        int count = counterService.increment();
        assertEquals(1, count);
    }

    @Test
    void decrement_shouldDecreaseCounter() {
        Counter counter = new Counter(2);
        when(counterRepository.findById(1L)).thenReturn(java.util.Optional.of(counter));
        when(counterRepository.save(any(Counter.class))).thenAnswer(i -> i.getArgument(0));
        int count = counterService.decrement();
        assertEquals(1, count);
    }

    @Test
    void getCount_shouldReturnCurrentCount() {
        Counter counter = new Counter(42);
        when(counterRepository.findById(1L)).thenReturn(java.util.Optional.of(counter));
        int count = counterService.getCount();
        assertEquals(42, count);
    }

    @Test
    void getCount_shouldReturnZeroIfNotPresent() {
        when(counterRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        int count = counterService.getCount();
        assertEquals(0, count);
    }
} 