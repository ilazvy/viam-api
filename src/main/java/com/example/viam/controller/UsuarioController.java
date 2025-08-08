package com.example.viam.controller;

import com.example.viam.model.Usuario;
import com.example.viam.model.Perfil;
import com.example.viam.service.UsuarioService;
import com.example.viam.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Usuarios", description = "API para gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService; // <-- INYECTA PerfilService

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo usuario")
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario usuarioCreado = usuarioService.registrarUsuario(nuevoUsuario);

        // Crear perfil automáticamente
        Perfil perfil = new Perfil();
        perfil.setUsuario(usuarioCreado);
        perfil.setActivo(true);
        perfil.setBiografia("");
        perfil.setFotoPerfil("");
        perfil.setUltimoAcceso(new Date()); // <-- CORREGIDO: usa java.util.Date
        perfilService.crear(perfil);

        return ResponseEntity.ok(usuarioCreado);
    }

    @Operation(summary = "Actualizar rol de usuario")
    @PatchMapping("/{id}/rol")
    public ResponseEntity<Usuario> actualizarRol(
            @PathVariable Long id,
            @RequestParam String nuevoRol) {
        return ResponseEntity.ok(usuarioService.actualizarRol(id, nuevoRol));
    }

    @Operation(summary = "Desactivar usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarUsuario(
            @PathVariable Long id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioActualizado) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuarioActualizado));
    }

    @RestController
    @RequestMapping("/api/ejemplo")
    public class EjemploController {

        @GetMapping
        public ResponseEntity<String> obtenerEjemplo() {
            return ResponseEntity.ok("Hola desde Swagger");
        }
    }
}