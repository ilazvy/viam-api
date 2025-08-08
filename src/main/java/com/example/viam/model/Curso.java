package com.example.viam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@Entity

@Table(name = "cursos")
public class Curso {
    public Curso() {
        // Constructor por defecto requerido por JPA
    }
    @Builder
    public Curso(Long id, String titulo, String slug, String descripcion, String urlImagen,
                 Usuario instructor, Date fechaCreacion, Nivel nivel, Integer duracionHoras,
                 Integer puntajeMaximo, EstadoCurso estado, List<Tema> temas, Boolean activo) {
        this.id = id;
        this.titulo = titulo;
        this.slug = slug;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.instructor = instructor;
        this.fechaCreacion = fechaCreacion;
        this.nivel = nivel;
        this.duracionHoras = duracionHoras;
        this.puntajeMaximo = puntajeMaximo;
        this.estado = estado;
        this.temas = temas;
        this.activo = activo;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "url_imagen")
    private String urlImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario instructor;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    @Column(name = "duracion_horas")
    private Integer duracionHoras;

    @Column(name = "puntaje_maximo")
    private Integer puntajeMaximo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCurso estado = EstadoCurso.BORRADOR;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tema> temas;

    @Column(nullable = false)
    private Boolean activo = true;

    public enum Nivel {
        PRINCIPIANTE, INTERMEDIO, AVANZADO
    }

    public enum EstadoCurso {
        ACTIVO, BORRADOR
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getSlug() { return slug; }
    public String getDescripcion() { return descripcion; }
    public String getUrlImagen() { return urlImagen; }
    public Usuario getInstructor() { return instructor; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public Nivel getNivel() { return nivel; }
    public Integer getDuracionHoras() { return duracionHoras; }
    public Integer getPuntajeMaximo() { return puntajeMaximo; }
    public EstadoCurso getEstado() { return estado; }
    public List<Tema> getTemas() { return temas; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setSlug(String slug) { this.slug = slug; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
    public void setInstructor(Usuario instructor) { this.instructor = instructor; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
    public void setDuracionHoras(Integer duracionHoras) { this.duracionHoras = duracionHoras; }
    public void setPuntajeMaximo(Integer puntajeMaximo) { this.puntajeMaximo = puntajeMaximo; }
    public void setEstado(EstadoCurso estado) { this.estado = estado; }
    public void setTemas(List<Tema> temas) { this.temas = temas; }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}