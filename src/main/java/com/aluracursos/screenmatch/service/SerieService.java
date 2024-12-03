package com.aluracursos.screenmatch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obtenerSeries() {
        return convierteDtos(repositorio.findAll());
    }

    public List<SerieDTO> seriesTop5() {
        return convierteDtos(repositorio.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> convierteDtos(List<Serie> serie) {
        return serie.stream()
                .map(s -> new SerieDTO(s.getTitulo(), s.getTotaldeTemporadas(), s.getEvaluacion(),
                        s.getGenero(), s.getActores(), s.getPoster(), s.getSinopsis()))
                .collect(Collectors.toList());
    }
}
