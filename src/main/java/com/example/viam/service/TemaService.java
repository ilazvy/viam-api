package com.example.viam.service;

import com.example.viam.model.Tema;
import java.util.List;
import java.util.Optional;

public interface TemaService {
    Tema save(Tema tema);
    List<Tema> findAll();
    Optional<Tema> findById(Long id);
    void deleteById(Long id);
}