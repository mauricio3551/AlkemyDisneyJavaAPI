package com.Disney.Alkemy.model.dto;

import com.Disney.Alkemy.model.entity.PeliculaSerie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GeneroDto {

    private Long idGenero;
    private String nombre;
    private List<PeliculaSerieDto> peliculaSerieDtoSet = new ArrayList<>();

    public GeneroDto() {
    }

    //Es lo que tiene que ingresar el usuario
    public GeneroDto(Long idGenero, String nombre) {
        this.idGenero = idGenero;
        this.nombre = nombre;
    }

    //Me devuelve la BD
    public GeneroDto(Long idGenero, String nombre, List<PeliculaSerie> peliculaSerieDtoSet) {
        this.idGenero = idGenero;
        this.nombre = nombre;
        this.peliculaSerieDtoSet = tomarPeliculas(peliculaSerieDtoSet);
    }

    private List<PeliculaSerieDto> tomarPeliculas(List<PeliculaSerie> set){
        return set.stream()
                .map(peliculaSerie -> new PeliculaSerieDto(
                        peliculaSerie.getIdPeliculaSerie(),
                        peliculaSerie.getTitulo(),
                        peliculaSerie.getFechaCreacion(),
                        peliculaSerie.getImagen(),
                        peliculaSerie.getCalificacion()
                )).collect(Collectors.toList());
    }
}
