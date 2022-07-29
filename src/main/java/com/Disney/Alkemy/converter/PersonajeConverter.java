package com.Disney.Alkemy.converter;

import com.Disney.Alkemy.dto.PersonajeDto;
import com.Disney.Alkemy.entity.Personaje;
import org.springframework.stereotype.Component;

@Component
public class PersonajeConverter {

    public PersonajeDto toDto(Personaje personaje){
        return new PersonajeDto(
                personaje.getIdPersonaje(),
                personaje.getNombre(),
                personaje.getPeso(),
                personaje.getHistoria(),
                personaje.getPeliculaSerieSet()
        );
    }

    public Personaje toEntity(PersonajeDto personajeDto){
        return new Personaje(
                personajeDto.getIdPersonaje(),
                personajeDto.getNombre(),
                personajeDto.getPeso(),
                personajeDto.getHistoria()
        );
    }
}
