package com.example.viam.repository;

import com.example.viam.model.Lectura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LecturaRepository extends JpaRepository<Lectura, Long> {
    List<Lectura> findByActivoTrue();
}