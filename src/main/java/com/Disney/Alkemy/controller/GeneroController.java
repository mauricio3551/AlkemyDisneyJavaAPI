package com.Disney.Alkemy.controller;

import com.Disney.Alkemy.dto.GeneroDto;
import com.Disney.Alkemy.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genero")
public class GeneroController {

    private final GeneroService generoService;

    @Autowired
    public GeneroController(GeneroService generoService){
        this.generoService = generoService;
    }

    @GetMapping()
    public ResponseEntity<List<GeneroDto>> getGeneros(){
        return new ResponseEntity<>(this.generoService.getGeneros(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<GeneroDto> postGenero(GeneroDto generoDto){
        return new ResponseEntity<>(this.generoService.postGenero(generoDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<GeneroDto> putGenero(@RequestParam Long id, @RequestBody GeneroDto generoDto){
        return new ResponseEntity<>(this.generoService.putGenero(id, generoDto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity deleteGenero(@RequestParam Long id){
        this.generoService.deleteGenero(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
