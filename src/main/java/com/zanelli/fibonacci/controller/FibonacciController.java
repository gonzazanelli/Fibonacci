package com.zanelli.fibonacci.controller;

import com.zanelli.fibonacci.service.FibonacciService;
import com.zanelli.fibonacci.service.FibonacciStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {
    private final FibonacciService service;
    private final FibonacciStatsService statsService;

    public FibonacciController(FibonacciService service, FibonacciStatsService statsService) {
        this.service = service;
        this.statsService = statsService;
    }

    @GetMapping("/{n}")
    public ResponseEntity<?> getFibonacci(@PathVariable int n) {
        if (n < 0) {
            return ResponseEntity
                    .badRequest()
                    .body("Error, el valor es negativo");
        }
        if (n > 5000) {
            return ResponseEntity
                    .badRequest()
                    .body("Error, valor supera el rango de 5000");
        }

        BigInteger result = service.getFibonacci(n);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(statsService.getStats());
    }
}
