package com.Disney.Alkemy.controller;

import com.Disney.Alkemy.model.dto.PersonajeDto;
import com.Disney.Alkemy.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/characters")
public class PersonajeController {

    private final PersonajeService personajeService;

    @Autowired
    public PersonajeController(PersonajeService personajeService){
        this.personajeService = personajeService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonajeDto>> getPersonajes(){
        return new ResponseEntity<>(this.personajeService.getPersonaje(), HttpStatus.OK);
    }

    @GetMapping(params = "age")
    public ResponseEntity<List<PersonajeDto>> getPersonajesByEdad
            (@RequestParam("age") int edad){
        return new ResponseEntity<>(this.personajeService.getPersonajeByEdad(edad), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<PersonajeDto>> getPersonajeByNombre
            (@RequestParam("name") String nombre){
        return new ResponseEntity<>(this.personajeService.getPersonajeByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(params = "movies")
    public ResponseEntity<List<PersonajeDto>> getPersonajeByPeliculaSerie
            (@RequestParam("movies") int idPeliculaSerie){
        return new ResponseEntity<>(this.personajeService.getPersonajeByPeliculaSerie(idPeliculaSerie), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<PersonajeDto> postPersonaje(@RequestBody PersonajeDto personajeDto){
        return new ResponseEntity<>(this.personajeService.postPersonaje(personajeDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<PersonajeDto> putPersonaje(@RequestParam Long id, @RequestBody PersonajeDto personajeDto){
        return new ResponseEntity<>(this.personajeService.putPersonaje(id, personajeDto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity deletePersonaje(@RequestParam Long id){
        this.personajeService.deletePersonaje(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
