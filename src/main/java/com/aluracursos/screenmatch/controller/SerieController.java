package com.aluracursos.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {
    
@GetMapping("/series")//Mi Primer Enpoint

    public String mostrarMensaje(){
        return "Mi primer mensaje de backend en Web";
    }
}
