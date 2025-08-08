package com.example.viam.repository;

import com.example.viam.model.Curso;
import com.example.viam.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findBySlug(String slug);
    List<Curso> findByInstructor(Usuario instructor);
    List<Curso> findByEstado(Curso.EstadoCurso estado);
    List<Curso> findByNivel(Curso.Nivel nivel);
    long countByEstado(Curso.EstadoCurso estado);
    @Query("SELECT c FROM Curso c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Curso> buscarPorTituloODescripcion(String query);

}
