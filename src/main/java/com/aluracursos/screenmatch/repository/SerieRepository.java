package com.aluracursos.screenmatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;


public interface SerieRepository extends JpaRepository<Serie, Long>{

    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByEvaluacionDesc();

    List<Serie> findByGenero(Categoria categoria);

    // List<Serie> findByTotaldeTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totaldeTemporadas, Double evaluacion);

    // ************Querys Nativas fijas*************

    /*@Query(value = "SELECT * FROM series WHERE series.totalde_temporadas >= 5 AND series.evaluacion <= 9", nativeQuery = true)
    List<Serie> buscarPorQueryNativas(); */

    // ************Querys Nativas con variante*************
    @Query("SELECT s FROM Serie s WHERE s.totaldeTemporadas >= :totaldeTemporadas AND s.evaluacion <= :evaluacion")
    List<Serie> buscarPorQueryNativas(int totaldeTemporadas, Double evaluacion);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> buscarEpisdioPorTitulo(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5 ")
    List<Episodio> buscarTop5Episodios(Serie serie);
}
