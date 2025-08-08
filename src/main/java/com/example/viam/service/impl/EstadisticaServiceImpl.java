package com.example.viam.service.impl;

import com.example.viam.model.Estadistica;
import com.example.viam.repository.EstadisticaRepository;
import com.example.viam.repository.UsuarioRepository;
import com.example.viam.service.EstadisticaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EstadisticaServiceImpl implements EstadisticaService {

    private final EstadisticaRepository estadisticaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Estadistica recopilarEstadisticas() {
        Estadistica stats = new Estadistica();
        stats.setUsuariosActivos((int) usuarioRepository.countByActivoTrue());
        stats.setFechaActualizacion(LocalDateTime.now());
        return estadisticaRepository.save(stats);
    }

    @Override
    public void actualizarEstadisticasDiarias() {
        // Este método podría ser llamado por un scheduler
        recopilarEstadisticas();
    }
}