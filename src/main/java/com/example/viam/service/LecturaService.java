package com.example.viam.service;

import com.example.viam.model.Lectura;
import java.util.List;
import java.util.Optional;

public interface LecturaService {
    Lectura save(Lectura lectura);
    List<Lectura> findAll();
    Optional<Lectura> findById(Long id);
    void deleteById(Long id);
}