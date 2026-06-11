package com.zanelli.fibonacci.service;

import com.zanelli.fibonacci.model.FibonacciResult;
import com.zanelli.fibonacci.repository.FibonacciRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class FibonacciService {
    private final FibonacciRepository repository;
    private final FibonacciStatsService statsService;

    public FibonacciService(FibonacciRepository repository, FibonacciStatsService statsService) {
        this.repository = repository;
        this.statsService = statsService;
    }

    @Transactional
    public BigInteger getFibonacci(int n) {
        // Registrar estadística
        statsService.record(n);

        // Busco en la base
        Optional<FibonacciResult> cached = repository.findById(n);
        if (cached.isPresent()) {
            return cached.get().getResult();
        }

        // Si no se encuentra en la base se calcula
        BigInteger result = calculate(n);

        // Guarda en la base el nuevo valor calculado
        repository.insertIfAbsent(n, result.toString());

        return result;
    }

    private BigInteger calculate(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        if (n == 2) return BigInteger.TWO;

        BigInteger prev2 = BigInteger.ONE; // f(1)
        BigInteger prev1 = BigInteger.TWO; // f(2)
        BigInteger current = BigInteger.TWO;

        for (int i = 3; i <= n; i++) {
            current = prev1.add(prev2);
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
}
