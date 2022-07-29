package com.Disney.Alkemy.service;

import com.Disney.Alkemy.converter.PeliculaSerieConverter;
import com.Disney.Alkemy.dto.PeliculaSerieDto;
import com.Disney.Alkemy.entity.PeliculaSerie;
import com.Disney.Alkemy.repository.PeliculaSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaSerieService {

    private final PeliculaSerieRepository peliculaSerieRepository;
    private final PeliculaSerieConverter peliculaSerieConverter;

    @Autowired
    public PeliculaSerieService(
            PeliculaSerieRepository peliculaSerieRepository,
            PeliculaSerieConverter peliculaSerieConverter){
        this.peliculaSerieConverter = peliculaSerieConverter;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    public List<PeliculaSerieDto> getPeliculaSerie(){
        return this.peliculaSerieRepository
                .findAll()
                .stream()
                .map(this.peliculaSerieConverter::toDto)
                .collect(Collectors.toList());
    }

    public PeliculaSerieDto postPeliculaSerie(PeliculaSerieDto peliculaSerieDto){
        PeliculaSerie nuevaPeliculaSerie =
                this.peliculaSerieRepository.save(this.peliculaSerieConverter.toEntity(peliculaSerieDto));
        return this.peliculaSerieConverter.toDto(nuevaPeliculaSerie);
    }

    public PeliculaSerieDto putPeliculaSerie(Long idPeliculaSerie, PeliculaSerieDto peliculaSerieDto){
        PeliculaSerie nuevosDatosPeliculaSerie = this.peliculaSerieConverter.toEntity(peliculaSerieDto);
        PeliculaSerie modificacionPeliculaSerie = this.peliculaSerieRepository
                .findById(idPeliculaSerie)
                .map(peliculaSerie -> {
                    peliculaSerie.setTitulo(nuevosDatosPeliculaSerie.getTitulo());
                    peliculaSerie.setFechaCreacion(nuevosDatosPeliculaSerie.getFechaCreacion());
                    peliculaSerie.setCalificacion(nuevosDatosPeliculaSerie.getCalificacion());
                    return this.peliculaSerieRepository.save(peliculaSerie);
                })
                .orElseGet(() -> this.peliculaSerieRepository.save(nuevosDatosPeliculaSerie));
        return this.peliculaSerieConverter.toDto(modificacionPeliculaSerie);
    }

    public void deletePeliculaSerie(Long idPeliculaSerie){
        this.peliculaSerieRepository.deleteById(idPeliculaSerie);
    }
}
