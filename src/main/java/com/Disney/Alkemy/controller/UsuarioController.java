package com.Disney.Alkemy.controller;

import com.Disney.Alkemy.model.dto.LoginDto;
import com.Disney.Alkemy.model.dto.UsuarioDto;
import com.Disney.Alkemy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> autenticarUsuario(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(this.usuarioService.autenticarUsuario(loginDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDto usuarioDto){
        return this.usuarioService.registrarUsuario(usuarioDto);
    }
}
