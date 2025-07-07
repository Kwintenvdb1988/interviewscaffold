package com.example.demo.counter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counter")
public class CounterController {
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/increment")
    public int increment() {
        return counterService.increment();
    }

    @PostMapping("/decrement")
    public int decrement() {
        return counterService.decrement();
    }

    @GetMapping
    public int getCount() {
        return counterService.getCount();
    }
} 