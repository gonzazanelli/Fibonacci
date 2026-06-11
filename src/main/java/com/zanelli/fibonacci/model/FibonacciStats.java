package com.zanelli.fibonacci.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "fibonacci_stat")
@Getter @Setter @NoArgsConstructor
public class FibonacciStats {
    @Id
    @Column(nullable = false, unique = true)
    private Integer n;

    @Column(nullable = false)
    private Long consultCount = 0L;

    @Column(nullable = false)
    private LocalDateTime firstConsultedAt;

    @Column(nullable = false)
    private LocalDateTime lastConsultedAt;

    public FibonacciStats(Integer n) {
        this.n = n;
        this.consultCount = 1L;
        this.firstConsultedAt = LocalDateTime.now();
        this.lastConsultedAt = LocalDateTime.now();
    }

    public void incrementCount() {
        this.consultCount++;
        this.lastConsultedAt = LocalDateTime.now();
    }
}
