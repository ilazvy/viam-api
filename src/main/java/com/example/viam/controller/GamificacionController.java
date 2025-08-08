package com.example.viam.controller;

import com.example.viam.model.Gamificacion;
import com.example.viam.service.GamificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamificaciones")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Gamificación", description = "API para sistema de gamificación")
public class GamificacionController {

    private final GamificacionService gamificacionService;

    @Operation(summary = "Obtener ranking de usuarios")
    @GetMapping("/ranking")
    public ResponseEntity<List<Gamificacion>> obtenerRanking() {
        return ResponseEntity.ok(gamificacionService.obtenerRankingGlobal());
    }

    @Operation(summary = "Obtener progreso del usuario actual")
    @GetMapping("/mi-progreso")
    public ResponseEntity<Gamificacion> obtenerMiProgreso(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(gamificacionService.obtenerProgresoUsuario(usuarioId));
    }

    // CRUD

    @Operation(summary = "Obtener gamificación por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Gamificacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(gamificacionService.obtenerPorId(id));
    }

    @Operation(summary = "Crear gamificación")
    @PostMapping
    public ResponseEntity<Gamificacion> crear(@RequestBody Gamificacion gamificacion) {
        return ResponseEntity.ok(gamificacionService.crear(gamificacion));
    }

    @Operation(summary = "Actualizar gamificación")
    @PutMapping("/{id}")
    public ResponseEntity<Gamificacion> actualizar(@PathVariable Long id, @RequestBody Gamificacion gamificacion) {
        return ResponseEntity.ok(gamificacionService.actualizar(id, gamificacion));
    }

    @Operation(summary = "Eliminar gamificación")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        gamificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todas las gamificaciones")
    @GetMapping
        public ResponseEntity<List<Gamificacion>> listarTodas() {
        return ResponseEntity.ok(gamificacionService.obtenerTodas());
    }
}