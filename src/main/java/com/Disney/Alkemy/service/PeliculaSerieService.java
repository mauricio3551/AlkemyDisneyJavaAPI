package com.Disney.Alkemy.service;

import com.Disney.Alkemy.model.converter.GeneroConverter;
import com.Disney.Alkemy.model.converter.PeliculaSerieConverter;
import com.Disney.Alkemy.model.converter.PersonajeConverter;
import com.Disney.Alkemy.model.dto.PeliculaSerieDto;
import com.Disney.Alkemy.model.entity.Genero;
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
    private final GeneroService generoService;
    private final GeneroConverter generoConverter;


    @Autowired
    public PeliculaSerieService(
            PeliculaSerieRepository peliculaSerieRepository,
            PeliculaSerieConverter peliculaSerieConverter,
            PersonajeService personajeService,
            PersonajeConverter personajeConverter,
            GeneroService generoService,
            GeneroConverter generoConverter) {
        this.peliculaSerieConverter = peliculaSerieConverter;
        this.peliculaSerieRepository = peliculaSerieRepository;
        this.personajeService = personajeService;
        this.personajeConverter = personajeConverter;
        this.generoService = generoService;
        this.generoConverter = generoConverter;

    }

    public List<PeliculaSerieDto> getPeliculaSerie(){
        return this.peliculaSerieRepository
                .findAll()
                .stream()
                .map(this.peliculaSerieConverter::toDto)
                .collect(Collectors.toList());
    }

    public List<PeliculaSerieDto> getPeliculaSerieByTitulo(String titulo){
        return this.peliculaSerieRepository
                .findByTitulo(titulo)
                .stream()
                .map(this.peliculaSerieConverter::toDto)
                .collect(Collectors.toList());
    }

    public List<PeliculaSerieDto> getPeliculaSerieByIdGenero(int idGenero){
        return this.peliculaSerieRepository
                .findByGenero(idGenero)
                .stream()
                .map(this.peliculaSerieConverter::toDto)
                .collect(Collectors.toList());
    }

    public List<PeliculaSerieDto> getPeliculaSerieByFechaCreacion(String order){
        if(order.toUpperCase().equals("DESC")){
            return this.peliculaSerieRepository
                    .findAllByFechaCreacionDesc()
                    .stream()
                    .map(this.peliculaSerieConverter::toDto)
                    .collect(Collectors.toList());
        } else {
            return this.peliculaSerieRepository
                    .findAllByFechaCreacionAsc()
                    .stream()
                    .map(this.peliculaSerieConverter::toDto)
                    .collect(Collectors.toList());
        }
    }

    public PeliculaSerieDto postPeliculaSerie(PeliculaSerieDto peliculaSerieDto){
        if(!peliculaSerieDto.getIdPersonajeList().isEmpty() && peliculaSerieDto.getIdGenero() != null){
            Genero genero =
                    this.generoConverter.toEntity(this.generoService.getGeneroById(peliculaSerieDto.getIdGenero()));

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
                    genero,
                    personajeList
            );

            personajeList.stream().map(personaje -> {
                personaje.getPeliculaSerieSet().add(nuevaPeliculaSerie);
                this.personajeService.postPersonaje(this.personajeConverter.toDto(personaje));
                return personaje;
            });

            genero.getPeliculaSerieSet().add(nuevaPeliculaSerie);

            this.generoService.postGenero(this.generoConverter.toDto(genero));
            this.peliculaSerieRepository.save(nuevaPeliculaSerie);
            return this.peliculaSerieConverter.toDto(nuevaPeliculaSerie);
        }else{
            return null;
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

    public PeliculaSerieDto putPersonajePeliculaSerie(Long idPeliculaSerie, Long idPersonaje){
        Personaje personaje = this.personajeConverter.toEntity(this.personajeService.getPersonajeById(idPersonaje));
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository.findById(idPeliculaSerie).orElseGet(null);
        if (personaje != null && peliculaSerie != null){
            peliculaSerie.getPersonajeList().add(personaje);
            personaje.getPeliculaSerieSet().add(peliculaSerie);
            this.peliculaSerieRepository.save(peliculaSerie);
            this.personajeService.postPersonaje(this.personajeConverter.toDto(personaje));
            return this.peliculaSerieConverter.toDto(peliculaSerie);
        }
        return null;
    }

    public PeliculaSerieDto putGeneroPeliculaSerie(Long idPeliculaSerie, Long idGenero){
        Genero genero = this.generoConverter.toEntity(this.generoService.getGeneroById(idGenero));
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository.findById(idPeliculaSerie).orElseGet(null);
        if (genero != null && peliculaSerie != null){
            peliculaSerie.setGenero(genero);
            genero.getPeliculaSerieSet().add(peliculaSerie);
            this.peliculaSerieRepository.save(peliculaSerie);
            this.generoService.postGenero(this.generoConverter.toDto(genero));
            return this.peliculaSerieConverter.toDto(peliculaSerie);
        }
        return null;
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

    public PeliculaSerieDto deletePersonajePeliculaSerie(Long idPeliculaSerie, Long idPersonaje){
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository.findById(idPeliculaSerie).orElseGet(null);
        Personaje personaje = this.personajeConverter.toEntity(this.personajeService.getPersonajeById(idPersonaje));
        if (peliculaSerie != null && personaje != null) {
            peliculaSerie.getPersonajeList().removeIf(p -> p.getIdPersonaje() == personaje.getIdPersonaje());
            this.peliculaSerieRepository.save(peliculaSerie);
        }
        return this.peliculaSerieConverter.toDto(peliculaSerie);
    }
}
