// src/main/java/com/example/viam/repository/PreguntaRepository.java
package com.example.viam.repository;

import com.example.viam.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByActivoTrue();
}