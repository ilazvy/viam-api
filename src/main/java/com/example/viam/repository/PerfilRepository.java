package com.example.viam.repository;

import com.example.viam.model.Perfil;
import com.example.viam.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByUsuario(Usuario usuario);
    boolean existsByUsuario(Usuario usuario);

    @Query("SELECT COUNT(p) FROM Perfil p WHERE p.cursosCompletados IS NOT EMPTY")
    long countUsuariosConCursosCompletados();
}