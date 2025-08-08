package com.example.viam.controller;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Lectura;
import com.example.viam.service.LecturaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturas")
public class LecturaController {

    private final LecturaService lecturaService;

    public LecturaController(LecturaService lecturaService) {
        this.lecturaService = lecturaService;
    }

    @GetMapping
    public ResponseEntity<List<Lectura>> getAllLecturas() {
        List<Lectura> lecturas = lecturaService.findAll();
        return ResponseEntity.ok(lecturas); // Devuelve 200 OK con la lista
    }


    @GetMapping("/{id}")
    public ResponseEntity<Lectura> getById(@PathVariable Long id) {
        return lecturaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lectura create(@RequestBody Lectura lectura) {
        return lecturaService.save(lectura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lectura> update(@PathVariable Long id, @RequestBody Lectura lectura) {
        return lecturaService.findById(id)
                .map(existing -> {
                    lectura.setId(id);
                    return ResponseEntity.ok(lecturaService.save(lectura));
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (lecturaService.findById(id).isPresent()) {
//            lecturaService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar lectura (borrado lógico)")
    public ResponseEntity<Void> desactivarLectura(@PathVariable Long id) {
        Lectura lectura = lecturaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lectura no encontrada"));
        lectura.setActivo(false);
        lecturaService.save(lectura);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Reactivar lectura")
    public ResponseEntity<Void> activarLectura(@PathVariable Long id) {
        Lectura lectura = lecturaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lectura no encontrada"));
        lectura.setActivo(true);
        lecturaService.save(lectura);
        return ResponseEntity.noContent().build();
    }
}