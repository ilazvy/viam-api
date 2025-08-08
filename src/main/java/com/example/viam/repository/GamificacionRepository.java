package com.example.viam.repository;

import com.example.viam.model.Gamificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamificacionRepository extends JpaRepository<Gamificacion, Long> {

    @Query("SELECT g FROM Gamificacion g ORDER BY g.puntos DESC")
    List<Gamificacion> findAllOrderByPuntosDesc();

    @Query("SELECT COUNT(g) FROM Gamificacion g WHERE g.nivel > :nivel")
    long countByNivelGreaterThan(int nivel);

    @Query("SELECT g FROM Gamificacion g WHERE :medalla MEMBER OF g.medallas")
    List<Gamificacion> findByMedalla(Gamificacion.Medalla medalla);
}