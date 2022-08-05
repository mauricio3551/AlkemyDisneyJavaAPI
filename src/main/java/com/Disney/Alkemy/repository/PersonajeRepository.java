package com.Disney.Alkemy.repository;

import com.Disney.Alkemy.model.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    @Query(value = "SELECT * FROM PERSONAJE WHERE PERSONAJE.edad = ?1", nativeQuery = true)
    List<Personaje> findByEdad(int edad);

    @Query(value = "SELECT * FROM PERSONAJE WHERE PERSONAJE.nombre = ?1", nativeQuery = true)
    List<Personaje> findByNombre(String nombre);

    @Query(value = "SELECT * " +
            "FROM PERSONAJE p " +
            "JOIN PELICULA_SERIE_PERSONAJE_LIST psp on (p.ID_PERSONAJE = psp.PERSONAJE_LIST_ID_PERSONAJE) " +
            "JOIN PELICULA_SERIE ps on (ps.ID_PELICULA_SERIE = psp.PELICULA_SERIE_SET_ID_PELICULA_SERIE) " +
            "WHERE ps.ID_PELICULA_SERIE = ?1",
            nativeQuery = true)
    List<Personaje> findByPeliculaSerie(int idPeliculaSerie);
}
