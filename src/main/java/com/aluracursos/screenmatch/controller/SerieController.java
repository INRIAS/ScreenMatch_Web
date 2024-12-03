package com.aluracursos.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.service.SerieService;

@RestController
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping("/series") // Mi Primer Enpoint
    public List<SerieDTO> obtenerSeries() {
        return servicio.obtenerSeries();
    }

    @GetMapping("/series/top5")
    public List<SerieDTO> seriesTop5() {
        return servicio.seriesTop5();
    }
}
