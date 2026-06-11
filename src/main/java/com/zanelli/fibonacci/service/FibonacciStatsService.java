package com.zanelli.fibonacci.service;

import com.zanelli.fibonacci.model.FibonacciStats;
import com.zanelli.fibonacci.repository.FibonacciStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FibonacciStatsService {
    private final FibonacciStatsRepository statsRepository;

    public FibonacciStatsService(FibonacciStatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Transactional
    public void record(int n) {
        Optional<FibonacciStats> existing = statsRepository.findById(n);
        if (existing.isPresent()) {
            existing.get().incrementCount();
        } else {
            statsRepository.save(new FibonacciStats(n));
        }
    }

    public List<FibonacciStats> getStats() {
        return statsRepository.findTopConsulted();
    }
}
