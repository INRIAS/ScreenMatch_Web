package com.aluracursos.screenmatch.principal;


import java.util.Arrays;
import java.util.List;

public class EjemploStream {
    
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Jesus","Jairo", "Andres", "Pepe", "Alex");

        nombres.stream()
            .sorted()
            .limit(4)
            .filter(n->n.startsWith("J"))
            .map(n->n.toUpperCase())
            .forEach(System.out::println);
    }
}
