package com.example.viam.service;

import com.example.viam.model.Curso;
import java.util.List;

public interface CursoService {
    Curso crearCurso(Curso curso);
    Curso actualizarCurso(Long id, Curso curso);
    List<Curso> listarCursosPorEstado(String estado);
    Curso obtenerCursoPorSlug(String slug);
    void eliminarCurso(Long id);
    List<Curso> buscarCursos(String keyword);
}