package com.aluracursos.screenmatch.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.repository.SerieRepository;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository repositorio;

    @GetMapping("/series") // Mi Primer Enpoint
    public List<SerieDTO> obtenerSeries() {
        return repositorio.findAll()
                .stream()
                .map(s -> new SerieDTO(s.getTitulo(), s.getTotaldeTemporadas(), s.getEvaluacion(),
                s.getGenero(), s.getActores(), s.getPoster(), s.getSinopsis()))
                .collect(Collectors.toList());
    }

}
