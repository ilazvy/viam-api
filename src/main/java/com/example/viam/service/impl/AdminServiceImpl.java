package com.example.viam.service.impl;

import com.example.viam.model.Curso;
import com.example.viam.model.Estadistica;
import com.example.viam.model.Usuario;
import com.example.viam.repository.CursoRepository;
import com.example.viam.repository.EstadisticaRepository;
import com.example.viam.repository.UsuarioRepository;
import com.example.viam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final EstadisticaRepository estadisticaRepository;

    @Override
    @Transactional
    public Estadistica generarEstadisticasGlobales() {
        Estadistica stats = new Estadistica();
        stats.setUsuariosActivos((int) usuarioRepository.countByActivoTrue());
        stats.setCursosActivos((int) cursoRepository.countByEstado(Curso.EstadoCurso.ACTIVO));
        return estadisticaRepository.save(stats);
    }

    @Override
    public List<Usuario> listarUsuariosPorRol(String rol) {
        return usuarioRepository.findByRol(Usuario.Rol.valueOf(rol.toUpperCase()));
    }

    @Override
    @Transactional
    public void desactivarCurso(Long cursoId) {
        cursoRepository.findById(cursoId).ifPresent(curso -> {
            curso.setEstado(Curso.EstadoCurso.BORRADOR);
            cursoRepository.save(curso);
        });
    }

    @Override
    @Transactional
    public void asignarRolInstructor(Long usuarioId) {
        usuarioRepository.findById(usuarioId).ifPresent(usuario -> {
            usuario.setRol(Usuario.Rol.INSTRUCTOR);
            usuarioRepository.save(usuario);
        });
    }
}