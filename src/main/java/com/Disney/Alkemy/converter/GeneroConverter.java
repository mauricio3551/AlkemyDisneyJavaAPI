package com.Disney.Alkemy.converter;

import com.Disney.Alkemy.dto.GeneroDto;
import com.Disney.Alkemy.entity.Genero;
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
