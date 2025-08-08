package com.example.viam.service.impl;

import com.example.viam.model.Gamificacion;
import com.example.viam.model.Perfil;
import com.example.viam.repository.GamificacionRepository;
import com.example.viam.service.GamificacionService;
import com.example.viam.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamificacionServiceImpl implements GamificacionService {

    private final GamificacionRepository gamificacionRepository;
    private final PerfilService perfilService;

    @Override
    public Gamificacion obtenerProgresoUsuario(Long usuarioId) {
        Perfil perfil = perfilService.obtenerPerfilActual(usuarioId);
        return gamificacionRepository.findAll().stream()
                .filter(g -> g.getPerfil().equals(perfil))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Progreso no encontrado"));
    }

    @Override
    @Transactional
    public void asignarPuntos(Long usuarioId, int puntos) {
        // Lógica de asignación de puntos
    }

    @Override
    public List<Gamificacion> obtenerRankingGlobal() {
        return gamificacionRepository.findAllOrderByPuntosDesc();
    }

    @Override
    @Transactional
    public void otorgarMedalla(Long usuarioId, String medalla) {
        // Lógica de otorgar medalla
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String mensaje) {
            super(mensaje);
        }
    }
    @Override
    public Gamificacion obtenerPorId(Long id) {
        return gamificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gamificación no encontrada"));
    }

    @Override
    @Transactional
    public Gamificacion crear(Gamificacion gamificacion) {
        return gamificacionRepository.save(gamificacion);
    }

    @Override
    @Transactional
    public Gamificacion actualizar(Long id, Gamificacion gamificacion) {
        Gamificacion existente = obtenerPorId(id);
        existente.setPerfil(gamificacion.getPerfil());
        existente.setPuntos(gamificacion.getPuntos());
        existente.setMedallas(gamificacion.getMedallas());
        existente.setNivel(gamificacion.getNivel());
        existente.setRankingGlobal(gamificacion.getRankingGlobal());
        return gamificacionRepository.save(existente);
    }

//    @Override
//    @Transactional
//    public void eliminar(Long id) {
//        gamificacionRepository.deleteById(id);
//    }
    @Override
    public List<Gamificacion> obtenerTodas() {
        return gamificacionRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Gamificacion gamificacion = obtenerPorId(id);
        gamificacion.setActivo(false);
        gamificacionRepository.save(gamificacion);
    }


}