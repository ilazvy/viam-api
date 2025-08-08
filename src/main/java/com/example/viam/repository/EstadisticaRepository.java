package com.example.viam.repository;

import com.example.viam.model.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {

    @Query(value = "SELECT * FROM estadisticas ORDER BY fecha_actualizacion DESC LIMIT 1", nativeQuery = true)
    Optional<Estadistica> findLatest();
}