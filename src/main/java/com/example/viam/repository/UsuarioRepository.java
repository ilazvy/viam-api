package com.example.viam.repository;

import com.example.viam.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByRol(Usuario.Rol rol);
    List<Usuario> findByRol(Usuario.Rol rol);
    long countByActivoTrue(); // <-- Agrega este método
}