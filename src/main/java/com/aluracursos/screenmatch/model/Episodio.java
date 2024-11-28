package com.aluracursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    private int temporada;
    private String titulo;
    private int numeroEpisodio;
    private double evaluacion;
    private LocalDate fechaDeLanzamiento;
    @ManyToOne
    private Serie serie;

    public Episodio() {
    }

    public Episodio(int numero, DataEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.evaluacion());
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0;
        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
        } catch (DateTimeParseException e) {
            this.fechaDeLanzamiento = null;
        }
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "temporada=" + temporada + ", titulo=" + titulo + ", numeroEpisodio=" + numeroEpisodio
                + ", evaluacion=" + evaluacion + ", fechaDeLanzamiento=" + fechaDeLanzamiento;
    }

}
