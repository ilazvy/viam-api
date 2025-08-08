package com.example.viam.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomProgresoRepository {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO progreso_curso (perfil_id, curso_id, porcentaje) VALUES (:perfilId, :cursoId, :porcentaje) " +
            "ON CONFLICT (perfil_id, curso_id) DO UPDATE SET porcentaje = :porcentaje", nativeQuery = true)
    void actualizarProgreso(@Param("perfilId") Long perfilId, @Param("cursoId") Long cursoId, @Param("porcentaje") Integer porcentaje);
}