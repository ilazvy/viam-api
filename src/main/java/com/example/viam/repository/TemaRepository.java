package com.example.viam.repository;

import com.example.viam.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    List<Tema> findByActivoTrue();
}