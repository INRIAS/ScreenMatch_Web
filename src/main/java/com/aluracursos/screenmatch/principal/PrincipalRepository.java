package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.DataSerie;
import com.aluracursos.screenmatch.model.DataTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.ConsumoApi;
import com.aluracursos.screenmatch.service.ConvertirDatos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrincipalRepository {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f33c21cc";
    private ConvertirDatos conversor = new ConvertirDatos();
    private List<DataSerie> dataSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series;
    private Optional<Serie> serieBuscada;

    public PrincipalRepository(SerieRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Series.
                    2 - Buscar Episodios.
                    3 - Mostrar Series Buscadas.
                    4 - Buscar Serie por Titulo.

                    5 - Top 5 de series.
                    6 - Buscar Serie por Categoria.
                    7 - Filtrar Series.
                    8 - Buscar Episodio por titulo.
                    9 - Top 5 episodios de Serie.
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostraSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarTop5Series();
                    break;
                case 6:
                    buscarSeriePorCategoria();
                    break;
                case 7:
                    filtrarSeriesPorTemporadaYEvaluacion();
                    break;
                case 8:
                    buscarEpisodioPorTitulo();
                    break;
                case 9:
                    buscarTop5Episodios();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DataSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DataSerie datos = conversor.obtenerDatos(json, DataSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSerie() {
        mostraSeriesBuscadas();

        System.out.println("Ingresar serie de los episodios a ver: ");
        var nombreSerie = teclado.nextLine();
        Optional<Serie> serie = series.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DataTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotaldeTemporadas(); i++) {
                var json = consumoApi
                        .obtenerDatos(
                                URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DataTemporadas datosTemporada = conversor.obtenerDatos(json, DataTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numeroTemporada(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }

    }

    private void buscarSerieWeb() {
        DataSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        // dataSeries.add(datos);
        // System.out.println(datos);
    }

    private void mostraSeriesBuscadas() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Buscar serie por titulo: ");
        var nombreSerie = teclado.nextLine();

        serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Serie encontrada en sistema: " + serieBuscada.get());
        } else {
            System.out.println("Serie no se encuentra en sistema");

        }
    }

    private void buscarTop5Series() {
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        topSeries.forEach(s -> System.out.println("Serie: " + s.getTitulo() + " Evaluacion: " + s.getEvaluacion()));
    }

    private void buscarSeriePorCategoria() {
        System.out.println("Categoria de series a buscar: ");
        var genero = teclado.nextLine();

        var categoria = Categoria.fromEspañol(genero);
        List<Serie> seriesPorCategorias = repositorio.findByGenero(categoria);

        System.out.println("Series encontradas por " + genero);
        seriesPorCategorias.forEach(c -> System.out.println(
                "Serie: " + c.getTitulo() + " / Evaliación: " + c.getEvaluacion() + " / Categoria: " + c.getGenero()));
        // seriesPorCategorias.forEach(System.out::println);
    }

    public void filtrarSeriesPorTemporadaYEvaluacion() {
        System.out.println("¿Filtrar séries con cuántas temporadas? ");
        var totalTemporadas = teclado.nextInt();
        teclado.nextLine();

        System.out.println("¿Con evaluación apartir de cuál valor? ");
        var evaluacion = teclado.nextDouble();
        teclado.nextLine();

        // List<Serie> filtroSeries =
        // repositorio.findByTotaldeTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(totalTemporadas,
        // evaluacion);
        List<Serie> filtroSeries = repositorio.buscarPorQueryNativas(totalTemporadas, evaluacion);
        System.out.println("*** Series filtradas ***");
        filtroSeries.forEach(s -> System.out.println(s.getTitulo() + "  - evaluacion: " + s.getEvaluacion()));
    }

    private void buscarEpisodioPorTitulo() {
        System.out.println("Ingrese el nombre del epidosio: ");
        var nombreEpisodio = teclado.nextLine();

        List<Episodio> episodiosEncontrados = repositorio.buscarEpisdioPorTitulo(nombreEpisodio);
        episodiosEncontrados
                .forEach(e -> System.out.printf("Serie: %s / Temporada: %s / Episodio: %s / Evaluacion: %s \n",
                        e.getSerie().getTitulo(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));
    }

    private void buscarTop5Episodios(){

        buscarSeriePorTitulo();
        if (serieBuscada.isPresent()) {
            Serie serie = serieBuscada.get();
            List<Episodio> topEpisodios= repositorio.buscarTop5Episodios(serie);
            topEpisodios.forEach(e -> System.out.printf("Serie: %s / Temporada: %s / Episodio: %s / Evaluacion: %s \n",
            e.getSerie().getTitulo(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));
        }

    }
}
