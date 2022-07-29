package com.Disney.Alkemy.service;

import com.Disney.Alkemy.converter.GeneroConverter;
import com.Disney.Alkemy.dto.GeneroDto;
import com.Disney.Alkemy.entity.Genero;
import com.Disney.Alkemy.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroConverter generoConverter;

    @Autowired
    public GeneroService(GeneroRepository generoRepository, GeneroConverter generoConverter){
        this.generoRepository = generoRepository;
        this.generoConverter = generoConverter;
    }

    public List<GeneroDto> getGeneros(){
        return this.generoRepository
                .findAll()
                .stream()
                .map(this.generoConverter::toDto)
                .collect(Collectors.toList());
    }

    public GeneroDto postGenero(GeneroDto generoDto){
        Genero nuevoGenero =
                this.generoRepository.save(this.generoConverter.toEntity(generoDto));
        return this.generoConverter.toDto(nuevoGenero);
    }

    public GeneroDto putGenero(Long idGenero, GeneroDto generoDto){
        Genero nuevosDatosGenero = this.generoConverter.toEntity(generoDto);
        Genero modificacionGenero = this.generoRepository
                .findById(idGenero)
                .map(genero -> {
                    genero.setNombre(nuevosDatosGenero.getNombre());
                    return this.generoRepository.save(genero);
                })
                .orElseGet(() -> this.generoRepository.save(nuevosDatosGenero));
        return this.generoConverter.toDto(modificacionGenero);
    }

    public void deleteGenero(Long idGenero){
        this.generoRepository.deleteById(idGenero);
    }


}
