package com.example.viam.service;

import com.example.viam.model.Respuesta;
import java.util.List;
import java.util.Optional;

public interface RespuestaService {
    Respuesta save(Respuesta respuesta);
    List<Respuesta> findAll();
    Optional<Respuesta> findById(Long id);
    void deleteById(Long id);
}