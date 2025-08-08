package com.example.viam.service;

import com.example.viam.model.Actividad;
import java.util.List;
import java.util.Optional;

public interface ActividadService {
    Actividad save(Actividad actividad);
    List<Actividad> findAll();
    Optional<Actividad> findById(Long id);
    void deleteById(Long id);
}