package com.example.viam.service;

import com.example.viam.model.Estadistica;
import com.example.viam.model.Usuario;
import java.util.List;

public interface AdminService {
    Estadistica generarEstadisticasGlobales();
    List<Usuario> listarUsuariosPorRol(String rol);
    void desactivarCurso(Long cursoId);
    void asignarRolInstructor(Long usuarioId);
}