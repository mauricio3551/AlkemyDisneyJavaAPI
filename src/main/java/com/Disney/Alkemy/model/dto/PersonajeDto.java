package com.Disney.Alkemy.model.dto;

import com.Disney.Alkemy.model.entity.PeliculaSerie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonajeDto {

    private Long idPersonaje;
    private String nombre;
    private int peso;
    private int edad;
    private String historia;
    private String imagen;
    private List<PeliculaSerieDto> peliculaSerieDtoSet = new ArrayList<>();

    public PersonajeDto() {
    }

    public PersonajeDto(Long idPersonaje, String nombre, int peso, int edad, String historia, String imagen) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.historia = historia;
        this.imagen = imagen;
    }

    public PersonajeDto(Long idPersonaje,
                        String nombre,
                        int peso,
                        int edad,
                        String historia,
                        String imagen,
                        List<PeliculaSerie> peliculaSerieDtoSet) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.historia = historia;
        this.imagen = imagen;
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
