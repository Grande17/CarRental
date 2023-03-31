package com.grande;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {

    Optional<Car> findByPlates(String plates);
    void deleteByPlates(String plates);
}
