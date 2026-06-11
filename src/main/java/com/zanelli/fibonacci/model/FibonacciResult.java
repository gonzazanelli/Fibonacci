package com.zanelli.fibonacci.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;

@Entity
@Table(name = "fibonacci_result")
@Getter @Setter @NoArgsConstructor
public class FibonacciResult {
    @Id
    @Column(nullable = false, unique = true)
    private Integer n;

    @Column(nullable = false, columnDefinition = "TEXT")
    private BigInteger result;

    public FibonacciResult(Integer n, BigInteger result) {
        this.n = n;
        this.result = result;
    }
}
