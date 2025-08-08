package com.example.viam.controller;

import com.example.viam.exception.ResourceNotFoundException;
import com.example.viam.model.Tema;
import com.example.viam.service.TemaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temas")
public class TemaController {

    private final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping
    public List<Tema> getAll() {
        return temaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id) {
        return temaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tema create(@RequestBody Tema tema) {
        return temaService.save(tema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tema> update(@PathVariable Long id, @RequestBody Tema tema) {
        return temaService.findById(id)
                .map(existing -> {
                    tema.setId(id);
                    return ResponseEntity.ok(temaService.save(tema));
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (temaService.findById(id).isPresent()) {
//            temaService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar tema (borrado lógico)")
    public ResponseEntity<Void> desactivarTema(@PathVariable Long id) {
        Tema tema = temaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema no encontrado"));
        tema.setActivo(false);
        temaService.save(tema);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Reactivar tema")
    public ResponseEntity<Void> activarTema(@PathVariable Long id) {
        Tema tema = temaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema no encontrado"));
        tema.setActivo(true);
        temaService.save(tema);
        return ResponseEntity.noContent().build();
    }
}