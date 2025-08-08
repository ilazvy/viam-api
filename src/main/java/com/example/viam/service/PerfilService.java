package com.example.viam.service;

import com.example.viam.model.Perfil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PerfilService {
    Perfil obtenerPerfilActual(Long usuarioId);
    Perfil actualizarBiografia(Long usuarioId, String biografia);
    String actualizarFotoPerfil(Long usuarioId, MultipartFile archivo);
    List<Perfil> obtenerTodos();
    Perfil obtenerPorId(Long id);
    Perfil crear(Perfil perfil);
    Perfil actualizar(Long id, Perfil perfil);
    void eliminar(Long id);
}