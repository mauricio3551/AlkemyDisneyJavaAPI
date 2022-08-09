package com.Disney.Alkemy.controller;

import com.Disney.Alkemy.model.dto.PeliculaSerieDto;
import com.Disney.Alkemy.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
public class PeliculaSerieController {

    private final PeliculaSerieService peliculaSerieService;

    @Autowired
    public PeliculaSerieController(PeliculaSerieService peliculaSerieService){
        this.peliculaSerieService = peliculaSerieService;
    }

    @GetMapping()
    public ResponseEntity<List<PeliculaSerieDto>> getPeliculasSeries(){
        return new ResponseEntity<>(this.peliculaSerieService.getPeliculaSerie(), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<PeliculaSerieDto>> getPeliculaSerieByTitulo(@RequestParam("name") String titulo){
        return new ResponseEntity<>(this.peliculaSerieService.getPeliculaSerieByTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping(params = "genre")
    public ResponseEntity<List<PeliculaSerieDto>> getPeliculaSerieByGenero(@RequestParam("genre") int idGenero){
        return new ResponseEntity<>(this.peliculaSerieService.getPeliculaSerieByIdGenero(idGenero), HttpStatus.OK);
    }

    @GetMapping(params = "order")
    public ResponseEntity<List<PeliculaSerieDto>> getPeliculaSerieByFechaCreacion(@RequestParam("order") String order){
        return new ResponseEntity<>(this.peliculaSerieService.getPeliculaSerieByFechaCreacion(order), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<PeliculaSerieDto> postPeliculaSerie(@RequestBody PeliculaSerieDto peliculaSerieDto){
        return new ResponseEntity<>(this.peliculaSerieService.postPeliculaSerie(peliculaSerieDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<PeliculaSerieDto> putPeliculaSerie
            (@RequestParam Long id, @RequestBody PeliculaSerieDto peliculaSerieDto){
        return new ResponseEntity<>(this.peliculaSerieService.putPeliculaSerie(id, peliculaSerieDto), HttpStatus.OK);
    }

    @PutMapping("/{idPelicula}/characters/{idPersonaje}")
    public ResponseEntity<PeliculaSerieDto> putPersonajePeliculaSerie
            (@PathVariable("idPelicula") Long idPelicula, @PathVariable("idPersonaje") Long idPersonaje){
        return new ResponseEntity<>
                (this.peliculaSerieService.putPersonajePeliculaSerie(idPelicula, idPersonaje), HttpStatus.OK);
    }

    @PutMapping("/{idPelicula}/imagen")
    public ResponseEntity<PeliculaSerieDto> putAgregarImagen
            (@PathVariable("idPelicula")  Long idPelicula, @RequestBody MultipartFile imagen){
        return new ResponseEntity<>(this.peliculaSerieService.putAgregarImagen(imagen, idPelicula), HttpStatus.OK);
    }

    @PutMapping("/{idPelicula}/genre/{idGenero}")
    public ResponseEntity<PeliculaSerieDto> putAgregarGenero
            (@PathVariable("idPelicula") Long idPelicula, @PathVariable("idGenero") Long idGenero){
        return new ResponseEntity<>
                (this.peliculaSerieService.putGeneroPeliculaSerie(idPelicula, idGenero), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity deletePeliculaSerie(@RequestParam Long id){
        this.peliculaSerieService.deletePeliculaSerie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idPelicula}/characters/{idPersonaje}")
    public ResponseEntity<PeliculaSerieDto> deletePersonajePeliculaSerie
            (@PathVariable("idPelicula") Long idPelicula, @PathVariable("idPersonaje") Long idPersonaje){
        return new ResponseEntity<>
                (this.peliculaSerieService.deletePersonajePeliculaSerie(idPelicula, idPersonaje), HttpStatus.OK);
    }
}
