package com.zanelli.fibonacci.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class FibonacciControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void testN0() throws Exception {
        mockMvc.perform(get("/api/fibonacci/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    void testN10() throws Exception {
        mockMvc.perform(get("/api/fibonacci/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("89"));
    }

    @Test
    void testN50() throws Exception {
        mockMvc.perform(get("/api/fibonacci/50"))
                .andExpect(status().isOk())
                .andExpect(content().string("20365011074"));
    }

    @Test
    void testNegativo() throws Exception {
        mockMvc.perform(get("/api/fibonacci/-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMayorA5000() throws Exception {
        mockMvc.perform(get("/api/fibonacci/5001"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testN5000() throws Exception {
        mockMvc.perform(get("/api/fibonacci/5000"))
                .andExpect(status().isOk());
    }
}