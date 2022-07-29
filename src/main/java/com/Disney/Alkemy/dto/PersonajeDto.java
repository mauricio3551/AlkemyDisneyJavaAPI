package com.Disney.Alkemy.dto;

import com.Disney.Alkemy.entity.PeliculaSerie;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonajeDto {

    private Long idPersonaje;
    private String nombre;
    private int peso;
    private String historia;
    private Set<PeliculaSerieDto> peliculaSerieDtoSet = new HashSet<>();

    public PersonajeDto() {
    }

    public PersonajeDto(Long idPersonaje, String nombre, int peso, String historia) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.peso = peso;
        this.historia = historia;
    }

    public PersonajeDto(Long idPersonaje,
                        String nombre,
                        int peso,
                        String historia,
                        Set<PeliculaSerie> peliculaSerieDtoSet) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.peso = peso;
        this.historia = historia;
        this.peliculaSerieDtoSet = tomarPeliculas(peliculaSerieDtoSet);
    }

    private Set<PeliculaSerieDto> tomarPeliculas(Set<PeliculaSerie> set){
        return set.stream()
                .map(peliculaSerie -> new PeliculaSerieDto(
                        peliculaSerie.getIdPeliculaSerie(),
                        peliculaSerie.getTitulo(),
                        peliculaSerie.getFechaCreacion(),
                        peliculaSerie.getCalificacion()
                )).collect(Collectors.toSet());
    }
}
