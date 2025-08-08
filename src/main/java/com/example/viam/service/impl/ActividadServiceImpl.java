package com.example.viam.service.impl;

import com.example.viam.model.Actividad;
import com.example.viam.repository.ActividadRepository;
import com.example.viam.service.ActividadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadServiceImpl(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    @Override
    public Actividad save(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    @Override
    public List<Actividad> findAll() {
        return actividadRepository.findByActivoTrue();
    }

    @Override
    public Optional<Actividad> findById(Long id) {
        Optional<Actividad> actividad = actividadRepository.findById(id);
        return actividad.filter(Actividad::isActivo);
    }

    @Override
    public void deleteById(Long id) {
        actividadRepository.findById(id).ifPresent(actividad -> {
            actividad.setActivo(false);
            actividadRepository.save(actividad);
        });
    }
}