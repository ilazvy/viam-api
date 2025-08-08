package com.example.viam.controller;

import com.example.viam.model.Perfil;
import com.example.viam.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Perfiles", description = "API para gestión de perfiles de usuario")
public class PerfilController {

    private final PerfilService perfilService;

    @Operation(summary = "Obtener perfil del usuario por ID")
    @GetMapping("/me")
    public ResponseEntity<Perfil> obtenerMiPerfil(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(perfilService.obtenerPerfilActual(usuarioId));
    }

    @Operation(summary = "Actualizar biografía")
    @PatchMapping("/biografia")
    public ResponseEntity<Perfil> actualizarBiografia(
            @RequestParam Long usuarioId,
            @RequestParam String biografia) {
        return ResponseEntity.ok(perfilService.actualizarBiografia(usuarioId, biografia));
    }

    @Operation(summary = "Obtener perfil por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.obtenerPorId(id));
    }

    @Operation(summary = "Listar todos los perfiles")
    @GetMapping
    public ResponseEntity<List<Perfil>> listarTodos() {
        return ResponseEntity.ok(perfilService.obtenerTodos());
    }

    @Operation(summary = "Eliminar perfil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        perfilService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Perfil> crear(@RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilService.crear(perfil));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> actualizar(@PathVariable Long id, @RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilService.actualizar(id, perfil));
    }
}