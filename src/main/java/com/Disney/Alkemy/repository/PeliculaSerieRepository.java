package com.Disney.Alkemy.repository;

import com.Disney.Alkemy.model.entity.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {

    @Query(value = "SELECT * FROM PELICULA_SERIE ps WHERE ps.titulo = ?1", nativeQuery = true)
    List<PeliculaSerie> findByTitulo(String titulo);

    @Query(value = "SELECT * " +
            "FROM PELICULA_SERIE ps " +
            "JOIN GENERO g on (ps.GENERO_ID_GENERO = g.ID_GENERO) " +
            "WHERE g.ID_GENERO = ?1",
            nativeQuery = true)
    List<PeliculaSerie> findByGenero(int idGenero);

    @Query(value = "SELECT * FROM PELICULA_SERIE ps ORDER BY ps.FECHA_CREACION DESC", nativeQuery = true)
    List<PeliculaSerie> findAllByFechaCreacionDesc();

    @Query(value = "SELECT * FROM PELICULA_SERIE ps ORDER BY ps.FECHA_CREACION ASC", nativeQuery = true)
    List<PeliculaSerie> findAllByFechaCreacionAsc();
}
