package com.example.viam.controller;



import com.example.viam.model.Curso;
import com.example.viam.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Cursos", description = "API para gestión de cursos")
public class CursoController {

    private final CursoService cursoService;

    @Operation(summary = "Crear nuevo curso")
    @PostMapping
    public ResponseEntity<Curso> crearCurso(
            @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }

    @Operation(summary = "Actualizar curso")
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(
            @PathVariable Long id,
            @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, curso));
    }

    @Operation(summary = "Listar cursos por estado")
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(
            @RequestParam(required = false, defaultValue = "ACTIVO") String estado) {
        return ResponseEntity.ok(cursoService.listarCursosPorEstado(estado));
    }

    @Operation(summary = "Obtener curso por slug")
    @GetMapping("/{slug}")
    public ResponseEntity<Curso> obtenerCurso(
            @PathVariable String slug) {
        return ResponseEntity.ok(cursoService.obtenerCursoPorSlug(slug));
    }

    // NUEVO: Obtener todos los cursos
    @Operation(summary = "Listar todos los cursos")
    @GetMapping("/todos")
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        return ResponseEntity.ok(cursoService.buscarCursos(""));
    }

    // NUEVO: Obtener curso por ID
    @Operation(summary = "Obtener curso por ID")
    @GetMapping("/id/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        // Puedes crear un método en el servicio para buscar por ID si no existe
        return ResponseEntity.ok(
                cursoService.listarCursosPorEstado("ACTIVO")
                        .stream()
                        .filter(c -> c.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Curso no encontrado"))
        );
    }

    // NUEVO: Eliminar curso por ID
    @Operation(summary = "Eliminar curso por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}