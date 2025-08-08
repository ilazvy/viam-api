// src/main/java/com/example/viam/service/impl/PreguntaServiceImpl.java
package com.example.viam.service.impl;

import com.example.viam.model.Pregunta;
import com.example.viam.repository.PreguntaRepository;
import com.example.viam.service.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private final PreguntaRepository preguntaRepository;

    public PreguntaServiceImpl(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Pregunta save(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public List<Pregunta> findAll() {
        return preguntaRepository.findByActivoTrue();
    }

    @Override
    public Optional<Pregunta> findById(Long id) {
        Optional<Pregunta> pregunta = preguntaRepository.findById(id);
        return pregunta.filter(Pregunta::isActivo);
    }

    @Override
    public void deleteById(Long id) {
        preguntaRepository.findById(id).ifPresent(pregunta -> {
            pregunta.setActivo(false);
            preguntaRepository.save(pregunta);
        });
    }
}