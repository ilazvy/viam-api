package com.example.viam.controller;

import com.example.viam.model.Estadistica;
import com.example.viam.model.Usuario;
import com.example.viam.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin", description = "Operaciones administrativas")
@PreAuthorize("hasRole('ADMIN')") // Seguridad a nivel de clase
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Obtener estadísticas globales")
    @GetMapping("/estadisticas")
    public ResponseEntity<Estadistica> obtenerEstadisticas() {
        return ResponseEntity.ok(adminService.generarEstadisticasGlobales());
    }

    @Operation(summary = "Listar usuarios por rol")
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuariosPorRol(
            @RequestParam String rol) {
        return ResponseEntity.ok(adminService.listarUsuariosPorRol(rol));
    }

    @Operation(summary = "Desactivar curso")
    @PatchMapping("/cursos/{id}/desactivar")
    public ResponseEntity<Void> desactivarCurso(
            @PathVariable Long id) {
        adminService.desactivarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Asignar rol instructor")
    @PostMapping("/usuarios/{id}/asignar-instructor")
    public ResponseEntity<Void> asignarRolInstructor(
            @PathVariable Long id) {
        adminService.asignarRolInstructor(id);
        return ResponseEntity.noContent().build();
    }

}