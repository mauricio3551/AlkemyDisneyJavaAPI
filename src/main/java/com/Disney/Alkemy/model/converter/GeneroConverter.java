package com.Disney.Alkemy.model.converter;

import com.Disney.Alkemy.model.dto.GeneroDto;
import com.Disney.Alkemy.model.entity.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroConverter {

    public GeneroDto toDto(Genero genero){
        return new GeneroDto(
                genero.getIdGenero(),
                genero.getNombre(),
                genero.getPeliculaSerieSet()
        );
    }

    public Genero toEntity(GeneroDto generoDto){
        return new Genero(
                generoDto.getIdGenero(),
                generoDto.getNombre()
        );
    }
}
