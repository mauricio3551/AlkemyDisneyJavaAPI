package com.Disney.Alkemy.service;

import com.Disney.Alkemy.converter.PersonajeConverter;
import com.Disney.Alkemy.dto.PersonajeDto;
import com.Disney.Alkemy.entity.Personaje;
import com.Disney.Alkemy.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonajeService {

    private final PersonajeRepository personajeRepository;
    private final PersonajeConverter personajeConverter;

    @Autowired
    public PersonajeService(PersonajeRepository personajeRepository, PersonajeConverter personajeConverter){
        this.personajeRepository = personajeRepository;
        this.personajeConverter = personajeConverter;
    }

    public List<PersonajeDto> getPersonaje(){
        return this.personajeRepository
                .findAll()
                .stream()
                .map(this.personajeConverter::toDto)
                .collect(Collectors.toList());
    }

    public PersonajeDto postPersonaje(PersonajeDto personajeDto){
        Personaje nuevoPersonaje = this.personajeRepository.save(this.personajeConverter.toEntity(personajeDto));
        return this.personajeConverter.toDto(nuevoPersonaje);
    }

    public PersonajeDto putPersonaje(Long idPersonaje, PersonajeDto personajeDto){
        Personaje nuevosDatosPersonaje = this.personajeConverter.toEntity(personajeDto);
        Personaje modificacionPersonaje = this.personajeRepository
                .findById(idPersonaje)
                .map(personaje -> {
                    personaje.setNombre(nuevosDatosPersonaje.getNombre());
                    personaje.setPeso(nuevosDatosPersonaje.getPeso());
                    personaje.setHistoria(nuevosDatosPersonaje.getHistoria());
                    return this.personajeRepository.save(personaje);
                })
                .orElseGet(() -> this.personajeRepository.save(nuevosDatosPersonaje));
        return this.personajeConverter.toDto(modificacionPersonaje);
    }

    public void deletePersonaje(Long idPersonaje){
        this.personajeRepository.deleteById(idPersonaje);
    }
}
