package com.example.viam.controller;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Actividad;
import com.example.viam.service.ActividadService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping
    public List<Actividad> getAll() {
        return actividadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> getById(@PathVariable Long id) {
        return actividadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Actividad create(@RequestBody Actividad actividad) {
        return actividadService.save(actividad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actividad> update(@PathVariable Long id, @RequestBody Actividad actividad) {
        return actividadService.findById(id)
                .map(existing -> {
                    actividad.setId(id);
                    return ResponseEntity.ok(actividadService.save(actividad));
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (actividadService.findById(id).isPresent()) {
//            actividadService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar actividad (borrado lógico)")
    public ResponseEntity<Void> desactivarActividad(@PathVariable Long id) {
        Actividad actividad = actividadService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
        actividad.setActivo(false);
        actividadService.save(actividad);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Reactivar actividad")
    public ResponseEntity<Void> activarActividad(@PathVariable Long id) {
        Actividad actividad = actividadService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
        actividad.setActivo(true);
        actividadService.save(actividad);
        return ResponseEntity.noContent().build();
    }
}