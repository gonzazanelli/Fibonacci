package com.zanelli.fibonacci.repository;

import com.zanelli.fibonacci.model.FibonacciResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface FibonacciRepository extends JpaRepository<FibonacciResult, Integer> {
    @Modifying
    @Query(value = """
        INSERT INTO fibonacci_result (n, result)
        VALUES (:n, :result)
        ON CONFLICT (n) DO NOTHING
        """, nativeQuery = true)
    void insertIfAbsent(@Param("n") int n, @Param("result") String result);
}
