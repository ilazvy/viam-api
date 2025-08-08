package com.example.viam.service;

import com.example.viam.model.Pregunta;
import java.util.List;
import java.util.Optional;

public interface PreguntaService {
    Pregunta save(Pregunta pregunta);
    List<Pregunta> findAll();
    Optional<Pregunta> findById(Long id);
    void deleteById(Long id);
}