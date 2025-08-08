package com.example.viam.controller;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Pregunta;
import com.example.viam.service.PreguntaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

    private final PreguntaService preguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping
    public List<Pregunta> getAll() {
        return preguntaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> getById(@PathVariable Long id) {
        return preguntaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pregunta create(@RequestBody Pregunta pregunta) {
        return preguntaService.save(pregunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pregunta> update(@PathVariable Long id, @RequestBody Pregunta pregunta) {
        return preguntaService.findById(id)
                .map(existing -> {
                    pregunta.setId(id);
                    return ResponseEntity.ok(preguntaService.save(pregunta));
                })
                .orElse(ResponseEntity.notFound().build());
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (preguntaService.findById(id).isPresent()) {
//            preguntaService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar pregunta (borrado lógico)")
    public ResponseEntity<Void> desactivarPregunta(@PathVariable Long id) {
        Pregunta pregunta = preguntaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));
        pregunta.setActivo(false);
        preguntaService.save(pregunta);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Reactivar pregunta")
    public ResponseEntity<Void> activarPregunta(@PathVariable Long id) {
        Pregunta pregunta = preguntaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));
        pregunta.setActivo(true);
        preguntaService.save(pregunta);
        return ResponseEntity.noContent().build();
    }
}