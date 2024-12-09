package com.aluracursos.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping() // Mi Primer Enpoint
    public List<SerieDTO> obtenerSeries() {
        return servicio.obtenerSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> seriesTop5() {
        return servicio.seriesTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes(){
        return servicio.obtenerLanzamientosMasRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id){
        return servicio.obtenerPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenertodasLasTemporadas(@PathVariable Long id){
        return servicio.obtenertodasLasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numeroTemporada){
        return servicio.obtenerTemporadasPorNumero(id,numeroTemporada);
    }

    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> obtenerSeriePorCategoria(@PathVariable String nombreGenero){
        return servicio.obtenerSeriePorCategoria(nombreGenero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> ObtenerTop5(@PathVariable Long id){
        return servicio.ObtenerTop5(id);
    }
}
