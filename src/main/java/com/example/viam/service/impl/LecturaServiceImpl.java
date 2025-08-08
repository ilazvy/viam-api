// src/main/java/com/example/viam/service/impl/LecturaServiceImpl.java
package com.example.viam.service.impl;

import com.example.viam.model.Lectura;
import com.example.viam.repository.LecturaRepository;
import com.example.viam.service.LecturaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturaServiceImpl implements LecturaService {

    private final LecturaRepository lecturaRepository;

    public LecturaServiceImpl(LecturaRepository lecturaRepository) {
        this.lecturaRepository = lecturaRepository;
    }

    @Override
    public Lectura save(Lectura lectura) {
        return lecturaRepository.save(lectura);
    }

    @Override
    public List<Lectura> findAll() {
        return lecturaRepository.findByActivoTrue();
    }

    @Override
    public Optional<Lectura> findById(Long id) {
        Optional<Lectura> lectura = lecturaRepository.findById(id);
        return lectura.filter(Lectura::isActivo);
    }

    @Override
    public void deleteById(Long id) {
        lecturaRepository.findById(id).ifPresent(lectura -> {
            lectura.setActivo(false);
            lecturaRepository.save(lectura);
        });
    }
}