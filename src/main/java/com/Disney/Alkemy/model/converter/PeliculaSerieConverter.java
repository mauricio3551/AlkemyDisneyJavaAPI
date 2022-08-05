package com.Disney.Alkemy.model.converter;

import com.Disney.Alkemy.model.dto.PeliculaSerieDto;
import com.Disney.Alkemy.model.entity.PeliculaSerie;
import org.springframework.stereotype.Component;

@Component
public class PeliculaSerieConverter {

    public PeliculaSerieDto toDto(PeliculaSerie peliculaSerie){
        return new PeliculaSerieDto(
                peliculaSerie.getIdPeliculaSerie(),
                peliculaSerie.getTitulo(),
                peliculaSerie.getFechaCreacion(),
                peliculaSerie.getImagen(),
                peliculaSerie.getCalificacion(),
                peliculaSerie.getGenero(),
                peliculaSerie.getPersonajeList()
        );
    }

    public PeliculaSerie toEntity(PeliculaSerieDto peliculaSerieDto){
        return new PeliculaSerie(
                peliculaSerieDto.getIdPeliculaSerie(),
                peliculaSerieDto.getTitulo(),
                peliculaSerieDto.getFechaCreacion(),
                peliculaSerieDto.getImagen(),
                peliculaSerieDto.getCalificacion()
        );
    }
}
