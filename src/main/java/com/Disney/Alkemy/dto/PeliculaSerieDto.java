package com.Disney.Alkemy.dto;

import com.Disney.Alkemy.entity.Genero;
import com.Disney.Alkemy.entity.Personaje;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PeliculaSerieDto {

    private Long idPeliculaSerie;
    private String titulo;
    private String fechaCreacion;
    private int calificacion;
    private GeneroDto genero;
    private Set<PersonajeDto> personajeSet = new HashSet<>();

    public PeliculaSerieDto() {
    }

    public PeliculaSerieDto(Long idPeliculaSerie, String titulo, String fechaCreacion, int calificacion) {
        this.idPeliculaSerie = idPeliculaSerie;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
    }

    public PeliculaSerieDto(Long idPeliculaSerie,
                            String titulo,
                            String fechaCreacion,
                            int calificacion,
                            Genero genero,
                            Set<Personaje> personajeSet) {
        this.idPeliculaSerie = idPeliculaSerie;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.genero = tomarGenero(genero);
        this.personajeSet = tomarPersonaje(personajeSet);
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

    private Set<PersonajeDto> tomarPersonaje(Set<Personaje> p){
        return p.stream()
                .map(personaje -> new PersonajeDto(
                        personaje.getIdPersonaje(),
                        personaje.getNombre(),
                        personaje.getPeso(),
                        personaje.getHistoria()
                )).collect(Collectors.toSet());
    }
}
