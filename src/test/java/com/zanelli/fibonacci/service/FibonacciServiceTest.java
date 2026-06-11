package com.zanelli.fibonacci.service;

import com.zanelli.fibonacci.model.FibonacciResult;
import com.zanelli.fibonacci.repository.FibonacciRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceTest {
    @Mock
    private FibonacciRepository repository;

    @Mock
    private FibonacciStatsService statsService;

    @InjectMocks
    private FibonacciService service;

    @BeforeEach
    void setUp() {
        // Limpiar interacciones
        reset(repository);
    }

    // Casos base

    @Test
    void testFibonacciN0() {
        when(repository.findById(0)).thenReturn(Optional.empty());
        assertEquals(BigInteger.ZERO, service.getFibonacci(0));
    }

    @Test
    void testFibonacciN1() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertEquals(BigInteger.ONE, service.getFibonacci(1));
    }

    @Test
    void testFibonacciN2() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertEquals(BigInteger.TWO, service.getFibonacci(2));
    }

    @Test
    void testFibonacciN10() {
        when(repository.findById(10)).thenReturn(Optional.empty());
        assertEquals(new BigInteger("89"), service.getFibonacci(10));
    }

    @Test
    void testFibonacciN50() {
        when(repository.findById(50)).thenReturn(Optional.empty());
        assertEquals(new BigInteger("20365011074"), service.getFibonacci(50));
    }

    // Cache

    @Test
    void testCacheHit() {
        // Simulo que ya está en BD
        FibonacciResult cached = new FibonacciResult(10, new BigInteger("55"));
        when(repository.findById(10)).thenReturn(Optional.of(cached));

        service.getFibonacci(10);

        // Verifico que NO calculó ni guardó nada
        verify(repository, never()).insertIfAbsent(anyInt(), anyString());
    }

    @Test
    void testCacheMissGuarda() {
        when(repository.findById(10)).thenReturn(Optional.empty());
        service.getFibonacci(10);
        verify(repository, times(1)).insertIfAbsent(10, "89");
    }

}
