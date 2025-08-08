package com.example.viam.service.impl;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Curso;
import com.example.viam.repository.CursoRepository;
import com.example.viam.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    @Transactional
    public Curso crearCurso(Curso curso) {
        curso.setSlug(generarSlug(curso.getTitulo()));
        curso.setFechaCreacion(new Date());
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        curso.setTitulo(cursoActualizado.getTitulo());
        curso.setDescripcion(cursoActualizado.getDescripcion());
        curso.setNivel(cursoActualizado.getNivel());
        curso.setUrlImagen(cursoActualizado.getUrlImagen());
        curso.setTemas(cursoActualizado.getTemas());
        curso.setDuracionHoras(cursoActualizado.getDuracionHoras());
        curso.setPuntajeMaximo(cursoActualizado.getPuntajeMaximo());
        curso.setEstado(cursoActualizado.getEstado());

        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listarCursosPorEstado(String estado) {
        return cursoRepository.findByEstado(Curso.EstadoCurso.valueOf(estado.toUpperCase()));
    }

    @Override
    public Curso obtenerCursoPorSlug(String slug) {
        return cursoRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
    }

    @Override
    @Transactional
    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        curso.setActivo(false); // Desactiva el curso (borrado lógico)
        cursoRepository.save(curso);
    }

    @Override
    public List<Curso> buscarCursos(String keyword) {
        return cursoRepository.buscarPorTituloODescripcion(keyword);
    }

    private String generarSlug(String titulo) {
        return titulo.toLowerCase().replace(" ", "-");
    }


}