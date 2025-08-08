package com.example.viam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "gamificacion")
public class Gamificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean activo = true;

    @OneToOne
    @JoinColumn(name = "perfil_id", nullable = false, unique = true)
    @JsonBackReference
    private Perfil perfil;

    @Column(nullable = false)
    private Integer puntos = 0;

    @ElementCollection
    @CollectionTable(name = "medallas", joinColumns = @JoinColumn(name = "gamificacion_id"))
    @Enumerated(EnumType.STRING)
    private List<Medalla> medallas;

    @Column(nullable = false)
    private Integer nivel = 1;

    @Column(name = "ranking_global")
    private Integer rankingGlobal;



    public enum Medalla {
        EXPLORADOR, FINALIZADOR, CRITICO, ESTRELLA_NACIENTE, MAESTRO
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public List<Medalla> getMedallas() {
        return medallas;
    }

    public void setMedallas(List<Medalla> medallas) {
        this.medallas = medallas;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getRankingGlobal() {
        return rankingGlobal;
    }

    public void setRankingGlobal(Integer rankingGlobal) {
        this.rankingGlobal = rankingGlobal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}