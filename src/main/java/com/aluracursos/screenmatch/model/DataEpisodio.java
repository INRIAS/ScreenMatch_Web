package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisodio(
    @JsonAlias("Title") String titulo,
    @JsonAlias("Episode") int numeroEpisodio,
    @JsonAlias("imdbRating") String evaluacion,
    @JsonAlias("Released") String fechaDeLanzamiento
    ) {
}
