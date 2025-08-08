package com.example.viam.utils;

import com.example.viam.model.Gamificacion;
import org.springframework.stereotype.Component;

@Component
public class GamificacionCalculator {

    public void actualizarPuntos(Gamificacion gamificacion, int puntos) {
        int nuevosPuntos = gamificacion.getPuntos() + puntos;
        gamificacion.setPuntos(nuevosPuntos);
        gamificacion.setNivel(calcularNivel(nuevosPuntos));
    }

    private int calcularNivel(int puntos) {
        return Math.min(puntos / 100, 10); // Máximo nivel 10
    }

    public boolean verificarMedalla(Gamificacion gamificacion, String medalla) {
        return gamificacion.getMedallas().contains(medalla);
    }
}
