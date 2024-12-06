package com.aluracursos.screenmatch.dto;

import com.aluracursos.screenmatch.model.Categoria;

public record SerieDTO(
        Long id,
        String titulo,
        int totaldeTemporadas,
        Double evaluacion,
        Categoria genero,
        String actores,
        String poster,
        String sinopsis) {

}
