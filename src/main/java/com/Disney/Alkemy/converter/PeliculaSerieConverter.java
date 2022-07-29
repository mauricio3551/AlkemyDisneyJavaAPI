package com.Disney.Alkemy.converter;

import com.Disney.Alkemy.dto.PeliculaSerieDto;
import com.Disney.Alkemy.entity.PeliculaSerie;
import org.springframework.stereotype.Component;

@Component
public class PeliculaSerieConverter {

    public PeliculaSerieDto toDto(PeliculaSerie peliculaSerie){
        return new PeliculaSerieDto(
                peliculaSerie.getIdPeliculaSerie(),
                peliculaSerie.getTitulo(),
                peliculaSerie.getFechaCreacion(),
                peliculaSerie.getCalificacion(),
                peliculaSerie.getGenero(),
                peliculaSerie.getPersonajeSet()
        );
    }

    public PeliculaSerie toEntity(PeliculaSerieDto peliculaSerieDto){
        return new PeliculaSerie(
                peliculaSerieDto.getIdPeliculaSerie(),
                peliculaSerieDto.getTitulo(),
                peliculaSerieDto.getFechaCreacion(),
                peliculaSerieDto.getCalificacion()
        );
    }
}
