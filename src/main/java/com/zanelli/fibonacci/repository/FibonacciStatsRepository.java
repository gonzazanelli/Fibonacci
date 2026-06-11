package com.zanelli.fibonacci.repository;

import com.zanelli.fibonacci.model.FibonacciStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FibonacciStatsRepository extends JpaRepository<FibonacciStats, Integer> {

    @Query("SELECT s FROM FibonacciStats s ORDER BY s.consultCount DESC")
    List<FibonacciStats> findTopConsulted();
}
