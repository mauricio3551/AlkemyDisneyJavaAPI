package com.Disney.Alkemy.service;

import com.Disney.Alkemy.model.converter.PeliculaSerieConverter;
import com.Disney.Alkemy.model.converter.PersonajeConverter;
import com.Disney.Alkemy.model.dto.PeliculaSerieDto;
import com.Disney.Alkemy.model.entity.PeliculaSerie;
import com.Disney.Alkemy.model.entity.Personaje;
import com.Disney.Alkemy.repository.PeliculaSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaSerieService {

    private final PeliculaSerieRepository peliculaSerieRepository;
    private final PeliculaSerieConverter peliculaSerieConverter;
    private final PersonajeService personajeService;
    private final PersonajeConverter personajeConverter;

    @Autowired
    public PeliculaSerieService(
            PeliculaSerieRepository peliculaSerieRepository,
            PeliculaSerieConverter peliculaSerieConverter,
            PersonajeService personajeService,
            PersonajeConverter personajeConverter) {
        this.peliculaSerieConverter = peliculaSerieConverter;
        this.peliculaSerieRepository = peliculaSerieRepository;
        this.personajeService = personajeService;
        this.personajeConverter = personajeConverter;
    }

    public List<PeliculaSerieDto> getPeliculaSerie(){
        return this.peliculaSerieRepository
                .findAll()
                .stream()
                .map(this.peliculaSerieConverter::toDto)
                .collect(Collectors.toList());
    }

    public PeliculaSerieDto postPeliculaSerie(PeliculaSerieDto peliculaSerieDto){
        if(!peliculaSerieDto.getIdPersonajeList().isEmpty()){
            List<Personaje> personajeList = peliculaSerieDto
                    .getIdPersonajeList()
                    .stream()
                    .map(idPersonaje ->
                            this.personajeConverter.toEntity(
                                    this.personajeService.getPersonajeById(idPersonaje)
                            ))
                    .collect(Collectors.toList());

            PeliculaSerie nuevaPeliculaSerie = new PeliculaSerie(
                    peliculaSerieDto.getIdPeliculaSerie(),
                    peliculaSerieDto.getTitulo(),
                    peliculaSerieDto.getFechaCreacion(),
                    peliculaSerieDto.getImagen(),
                    peliculaSerieDto.getCalificacion(),
                    personajeList
            );
            this.peliculaSerieRepository.save(nuevaPeliculaSerie);
            return this.peliculaSerieConverter.toDto(nuevaPeliculaSerie);
        }else{
            PeliculaSerie nuevaPeliculaSerie =
                    this.peliculaSerieRepository.save(this.peliculaSerieConverter.toEntity(peliculaSerieDto));
            return this.peliculaSerieConverter.toDto(nuevaPeliculaSerie);
        }
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

    public PeliculaSerieDto putAgregarImagen(MultipartFile imagen, Long idPeliculaSerie){
        if(!imagen.isEmpty()){
            Path imagenPath = Paths.get("src/main/resources/static/img/peliculaSerie/");
            String absolutPath = imagenPath.toFile().getAbsolutePath();
            try{
                byte[] bytes = imagen.getBytes();
                Path route = Paths.get(absolutPath + "/" + imagen.getOriginalFilename());
                Files.write(route, bytes);
                PeliculaSerie peliculaSerie =
                        this.peliculaSerieRepository.findById(idPeliculaSerie).orElseGet(null);
                peliculaSerie.setImagen(imagen.getOriginalFilename());
                return this.peliculaSerieConverter.toDto(this.peliculaSerieRepository.save(peliculaSerie));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public void deletePeliculaSerie(Long idPeliculaSerie){
        this.peliculaSerieRepository.deleteById(idPeliculaSerie);
    }
}
