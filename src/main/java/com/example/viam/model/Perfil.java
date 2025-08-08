package com.example.viam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @ManyToMany
    @JoinTable(
            name = "usuario_cursos",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Curso> cursosInscritos;

    @ManyToMany
    @JoinTable(
            name = "cursos_completados",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Curso> cursosCompletados;


    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;

    @Column(nullable = false)
    private boolean activo = true;


    @ElementCollection
    @CollectionTable(name = "progreso_curso", joinColumns = @JoinColumn(name = "perfil_id"))
    @MapKeyColumn(name = "curso_id") // <-- Debe coincidir con la columna real
    @Column(name = "porcentaje")
    private Map<Long, Integer> progresoPorCurso;

    @OneToOne(mappedBy = "perfil", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Gamificacion gamificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public void setCursosInscritos(List<Curso> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }

    public List<Curso> getCursosCompletados() {
        return cursosCompletados;
    }

    public void setCursosCompletados(List<Curso> cursosCompletados) {
        this.cursosCompletados = cursosCompletados;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Map<Long, Integer> getProgresoPorCurso() {
        return progresoPorCurso;
    }

    public void setProgresoPorCurso(Map<Long, Integer> progresoPorCurso) {
        this.progresoPorCurso = progresoPorCurso;
    }

    public Gamificacion getGamificacion() {
        return gamificacion;
    }

    public void setGamificacion(Gamificacion gamificacion) {
        this.gamificacion = gamificacion;
    }
}
