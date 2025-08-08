package com.example.viam.service;

import com.example.viam.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    Usuario actualizarUsuario(Long id, Usuario usuario);
    void desactivarUsuario(Long id);
    List<Usuario> listarUsuariosPorRol(String rol);
    boolean existeEmail(String email);
    Optional<Usuario> buscarPorId(Long id);
    long countByActivoTrue(); // <-- Agrega este método
    List<Usuario> listarTodos();
    Optional<Usuario> obtenerPorId(Long id);
    Usuario actualizarRol(Long id, String nuevoRol);
}