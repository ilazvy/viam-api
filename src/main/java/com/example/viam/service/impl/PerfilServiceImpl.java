// src/main/java/com/example/viam/service/impl/PerfilServiceImpl.java
package com.example.viam.service.impl;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Curso;
import com.example.viam.model.Perfil;
import com.example.viam.model.Usuario;
import com.example.viam.repository.CursoRepository;
import com.example.viam.repository.PerfilRepository;
import com.example.viam.repository.UsuarioRepository;
import com.example.viam.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioServiceImpl usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    @Override
    @Transactional
    public Perfil obtenerPerfilActual(Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Perfil perfil = perfilRepository.findByUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado"));
        perfil.setUltimoAcceso(new Date()); // <-- Actualiza el campo
        perfilRepository.save(perfil);      // <-- Guarda el cambio
        return perfil;
    }

    @Override
    @Transactional
    public Perfil actualizarBiografia(Long usuarioId, String biografia) {
        Perfil perfil = obtenerPerfilActual(usuarioId);
        perfil.setBiografia(biografia);
        return perfilRepository.save(perfil);
    }

    @Override
    public String actualizarFotoPerfil(Long usuarioId, MultipartFile archivo) {
        Perfil perfil = obtenerPerfilActual(usuarioId);
        String nombreArchivo = "profile-" + perfil.getId() + "-" + archivo.getOriginalFilename();
        perfil.setFotoPerfil("/uploads/" + nombreArchivo);
        perfilRepository.save(perfil);
        return perfil.getFotoPerfil();
    }



    @Override
    public Perfil obtenerPorId(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado"));
    }

    @Override
    @Transactional
    public Perfil crear(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    @Transactional
    public Perfil actualizar(Long id, Perfil perfil) {
        Perfil existente = obtenerPorId(id);
        existente.setUsuario(perfil.getUsuario());
        existente.setFotoPerfil(perfil.getFotoPerfil());
        existente.setBiografia(perfil.getBiografia());
        existente.setCursosInscritos(perfil.getCursosInscritos());
        existente.setCursosCompletados(perfil.getCursosCompletados());
        existente.setProgresoPorCurso(perfil.getProgresoPorCurso());
        existente.setUltimoAcceso(perfil.getUltimoAcceso());
        return perfilRepository.save(existente);
    }

//    @Override
//    @Transactional
//    public void eliminar(Long id) {
//        perfilRepository.deleteById(id);
//    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Perfil perfil = obtenerPorId(id);
        perfil.setActivo(false);
        perfilRepository.save(perfil);
    }

    @Override
    public List<Perfil> obtenerTodos() {
        return perfilRepository.findAll();
    }
}