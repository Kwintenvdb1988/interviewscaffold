package com.example.demo.counter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CounterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCount_shouldReturnZeroInitially() throws Exception {
        mockMvc.perform(get("/api/counter"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    void increment_shouldIncreaseCounter() throws Exception {
        // Start at 0, increment to 1
        mockMvc.perform(post("/api/counter/increment"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
        // Increment again, should be 2
        mockMvc.perform(post("/api/counter/increment"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void decrement_shouldDecreaseCounter() throws Exception {
        // Start at 0, decrement to -1
        mockMvc.perform(post("/api/counter/decrement"))
                .andExpect(status().isOk())
                .andExpect(content().string("-1"));
        // Decrement again, should be -2
        mockMvc.perform(post("/api/counter/decrement"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2"));
    }
} 