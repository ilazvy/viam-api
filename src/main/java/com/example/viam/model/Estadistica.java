package com.example.viam.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "estadisticas")
public class Estadistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuarios_activos", nullable = false)
    private Integer usuariosActivos;

    @Column(name = "cursos_activos", nullable = false)
    private Integer cursosActivos;

    @Column(name = "progreso_medio", nullable = false)
    private Double progresoMedio;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Double getProgresoMedio() {
        return progresoMedio;
    }

    public Integer getCursosActivos() {
        return cursosActivos;
    }

    public Integer getUsuariosActivos() {
        return usuariosActivos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuariosActivos(Integer usuariosActivos) {
        this.usuariosActivos = usuariosActivos;
    }

    public void setCursosActivos(Integer cursosActivos) {
        this.cursosActivos = cursosActivos;
    }

    public void setProgresoMedio(Double progresoMedio) {
        this.progresoMedio = progresoMedio;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}