package com.example.viam.controller;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Respuesta;
import com.example.viam.service.RespuestaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    private final RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @GetMapping
    public List<Respuesta> getAll() {
        return respuestaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getById(@PathVariable Long id) {
        return respuestaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Respuesta create(@RequestBody Respuesta respuesta) {
        return respuestaService.save(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> update(@PathVariable Long id, @RequestBody Respuesta respuesta) {
        return respuestaService.findById(id)
                .map(existing -> {
                    respuesta.setId(id);
                    return ResponseEntity.ok(respuestaService.save(respuesta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (respuestaService.findById(id).isPresent()) {
//            respuestaService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar respuesta (borrado lógico)")
    public ResponseEntity<Void> desactivarRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));
        respuesta.setActivo(false);
        respuestaService.save(respuesta);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Reactivar respuesta")
    public ResponseEntity<Void> activarRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));
        respuesta.setActivo(true);
        respuestaService.save(respuesta);
        return ResponseEntity.noContent().build();
    }
}