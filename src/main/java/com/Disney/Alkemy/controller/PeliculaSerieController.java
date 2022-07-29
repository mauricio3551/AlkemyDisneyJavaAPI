package com.Disney.Alkemy.controller;

import com.Disney.Alkemy.dto.PeliculaSerieDto;
import com.Disney.Alkemy.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/crear")
    public ResponseEntity<PeliculaSerieDto> postPeliculaSerie(PeliculaSerieDto peliculaSerieDto){
        return new ResponseEntity<>(this.peliculaSerieService.postPeliculaSerie(peliculaSerieDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<PeliculaSerieDto> putPeliculaSerie(@RequestParam Long id, @RequestBody PeliculaSerieDto peliculaSerieDto){
        return new ResponseEntity<>(this.peliculaSerieService.putPeliculaSerie(id, peliculaSerieDto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity deletePeliculaSerie(@RequestParam Long id){
        this.peliculaSerieService.deletePeliculaSerie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
