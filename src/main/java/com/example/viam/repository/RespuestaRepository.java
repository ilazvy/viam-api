package com.example.viam.repository;

import com.example.viam.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByActivoTrue();
}