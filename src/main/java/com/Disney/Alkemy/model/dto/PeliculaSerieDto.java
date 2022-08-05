package com.Disney.Alkemy.model.dto;

import com.Disney.Alkemy.model.entity.Genero;
import com.Disney.Alkemy.model.entity.Personaje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class PeliculaSerieDto {

    private Long idPeliculaSerie;
    private String titulo;
    private Date fechaCreacion;
    private String imagen;
    private int calificacion;
    private GeneroDto genero;
    @JsonIgnore
    private List<Long> idPersonajeList = new ArrayList<>();
    private List<PersonajeDto> personajeList = new ArrayList<>();

    public PeliculaSerieDto() {
    }

    public PeliculaSerieDto(Long idPeliculaSerie,
                            String titulo,
                            Date fechaCreacion,
                            String imagen,
                            int calificacion) {
        this.idPeliculaSerie = idPeliculaSerie;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.calificacion = calificacion;
    }

    public PeliculaSerieDto(Long idPeliculaSerie,
                            String titulo,
                            Date fechaCreacion,
                            String imagen,
                            int calificacion,
                            List<Long> idPersonajeList) {
        this.idPeliculaSerie = idPeliculaSerie;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.calificacion = calificacion;
        this.idPersonajeList = idPersonajeList;
    }

    public PeliculaSerieDto(Long idPeliculaSerie,
                            String titulo,
                            Date fechaCreacion,
                            String imagen,
                            int calificacion,
                            Genero genero,
                            List<Personaje> personajeList) {
        this.idPeliculaSerie = idPeliculaSerie;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.calificacion = calificacion;
        this.genero = tomarGenero(genero);
        this.personajeList = tomarPersonaje(personajeList);
    }

    private GeneroDto tomarGenero(Genero g){
        if (g == null) {
            return null;
        }
        return new GeneroDto(
                g.getIdGenero(),
                g.getNombre()
        );
    }

    private List<PersonajeDto> tomarPersonaje(List<Personaje> p){
        return p.stream()
                .map(personaje -> new PersonajeDto(
                        personaje.getIdPersonaje(),
                        personaje.getNombre(),
                        personaje.getPeso(),
                        personaje.getEdad(),
                        personaje.getHistoria(),
                        personaje.getImagen()
                )).collect(Collectors.toList());
    }
}
