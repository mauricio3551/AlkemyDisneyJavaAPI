package com.Disney.Alkemy.dto;

import com.Disney.Alkemy.entity.PeliculaSerie;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GeneroDto {

    private Long idGenero;
    private String nombre;
    private Set<PeliculaSerieDto> peliculaSerieDtoSet = new HashSet<>();

    public GeneroDto() {
    }

    public GeneroDto(Long idGenero, String nombre) {
        this.idGenero = idGenero;
        this.nombre = nombre;
    }

    public GeneroDto(Long idGenero, String nombre, Set<PeliculaSerie> peliculaSerieDtoSet) {
        this.idGenero = idGenero;
        this.nombre = nombre;
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
