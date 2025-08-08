package com.example.viam.service.impl;

import com.example.viam.model.Tema;
import com.example.viam.repository.TemaRepository;
import com.example.viam.service.TemaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemaServiceImpl implements TemaService {

    private final TemaRepository temaRepository;

    public TemaServiceImpl(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @Override
    public Tema save(Tema tema) {
        return temaRepository.save(tema);
    }

    @Override
    public List<Tema> findAll() {
        return temaRepository.findByActivoTrue();
    }

    @Override
    public Optional<Tema> findById(Long id) {
        Optional<Tema> tema = temaRepository.findById(id);
        return tema.filter(Tema::isActivo);
    }

    @Override
    public void deleteById(Long id) {
        temaRepository.findById(id).ifPresent(tema -> {
            tema.setActivo(false);
            temaRepository.save(tema);
        });
    }
}