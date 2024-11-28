package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION("Action", "Acción"),
    ROMANCE("Romnce", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");

    private String categoriaOMBD;
    private String categoriaEnEspanol;

    Categoria(String categoriaOMBD, String categoriaEnEspanol) {
        this.categoriaOMBD = categoriaOMBD;
        this.categoriaEnEspanol= categoriaEnEspanol;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOMBD.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Categoria fromEspañol(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEnEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
