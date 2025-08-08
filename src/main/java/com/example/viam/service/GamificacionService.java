package com.example.viam.service;

import com.example.viam.model.Gamificacion;
import java.util.List;

public interface GamificacionService {
    Gamificacion obtenerProgresoUsuario(Long usuarioId);

    void asignarPuntos(Long usuarioId, int puntos);

    List<Gamificacion> obtenerRankingGlobal();

    void otorgarMedalla(Long usuarioId, String medalla);
    // Métodos CRUD
    Gamificacion obtenerPorId(Long id);
    Gamificacion crear(Gamificacion gamificacion);
    Gamificacion actualizar(Long id, Gamificacion gamificacion);
    void eliminar(Long id);
    List<Gamificacion> obtenerTodas();
}