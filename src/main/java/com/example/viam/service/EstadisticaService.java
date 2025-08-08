package com.example.viam.service;

import com.example.viam.model.Estadistica;

public interface EstadisticaService {
    Estadistica recopilarEstadisticas();
    void actualizarEstadisticasDiarias();
}