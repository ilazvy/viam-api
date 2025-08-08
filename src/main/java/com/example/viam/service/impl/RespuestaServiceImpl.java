package com.example.viam.service.impl;

import com.example.viam.model.Respuesta;
import com.example.viam.repository.RespuestaRepository;
import com.example.viam.service.RespuestaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository respuestaRepository;

    public RespuestaServiceImpl(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @Override
    public Respuesta save(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    @Override
    public List<Respuesta> findAll() {
        return respuestaRepository.findByActivoTrue();
    }

    @Override
    public Optional<Respuesta> findById(Long id) {
        Optional<Respuesta> respuesta = respuestaRepository.findById(id);
        return respuesta.filter(Respuesta::isActivo);
    }

    @Override
    public void deleteById(Long id) {
        respuestaRepository.findById(id).ifPresent(respuesta -> {
            respuesta.setActivo(false);
            respuestaRepository.save(respuesta);
        });
    }
}