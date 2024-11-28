package com.aluracursos.screenmatch.model;

import java.util.List;
import java.util.OptionalDouble;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// import com.aluracursos.screenmatch.service.ConsultaChatGtp;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private int totaldeTemporadas;
    private Double evaluacion;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String actores;
    private String poster;
    private String sinopsis;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios;

    public Serie() {
    }

    public Serie(DataSerie dataSerie) {
        this.titulo = dataSerie.titulo();
        this.totaldeTemporadas = dataSerie.totaldeTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(dataSerie.evaluacion())).orElse(0);
        this.genero = Categoria.fromString(dataSerie.genero().split(",")[0].trim());
        // this.genero = Categoria.fromEspañol(dataSerie.genero().split(",")[0].trim());
        this.actores = dataSerie.actores();
        this.poster = dataSerie.poster();
        // this.sinopsis = ConsultaChatGtp.obtenerTraduccion(dataSerie.sinopsis());
        this.sinopsis = dataSerie.sinopsis();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotaldeTemporadas() {
        return totaldeTemporadas;
    }

    public void setTotaldeTemporadas(int totaldeTemporadas) {
        this.totaldeTemporadas = totaldeTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e->e.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return "Titulo= " + titulo + ", Total de Temporadas= " + totaldeTemporadas + ", Evaluación= " + evaluacion
                + ", Genero= " + genero + ", Actores= " + actores + ", Poster= " + poster + ", Sinopsis= " + sinopsis + ", Episodios= " + episodios;
    }

}
