package com.Disney.Alkemy.model.converter;

import com.Disney.Alkemy.model.dto.PersonajeDto;
import com.Disney.Alkemy.model.entity.Personaje;
import org.springframework.stereotype.Component;

@Component
public class PersonajeConverter {

    public PersonajeDto toDto(Personaje personaje){
        return new PersonajeDto(
                personaje.getIdPersonaje(),
                personaje.getNombre(),
                personaje.getPeso(),
                personaje.getEdad(),
                personaje.getHistoria(),
                personaje.getImagen(),
                personaje.getPeliculaSerieSet()
        );
    }

    public Personaje toEntity(PersonajeDto personajeDto){
        return new Personaje(
                personajeDto.getIdPersonaje(),
                personajeDto.getNombre(),
                personajeDto.getPeso(),
                personajeDto.getEdad(),
                personajeDto.getHistoria(),
                personajeDto.getImagen()
        );
    }
}
