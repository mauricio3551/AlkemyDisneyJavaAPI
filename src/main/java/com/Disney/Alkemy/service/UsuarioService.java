package com.Disney.Alkemy.service;

import com.Disney.Alkemy.model.converter.RoleConverter;
import com.Disney.Alkemy.model.converter.UsuarioConverter;
import com.Disney.Alkemy.model.dto.LoginDto;
import com.Disney.Alkemy.model.dto.UsuarioDto;
import com.Disney.Alkemy.model.entity.Role;
import com.Disney.Alkemy.model.entity.Usuario;
import com.Disney.Alkemy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UsuarioService{

    private UsuarioRepository usuarioRepository;
    private AuthenticationManager authenticationManager;
    private UsuarioConverter usuarioConverter;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private RoleConverter roleConverter;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepositor,
                          AuthenticationManager authenticationManagery,
                          UsuarioConverter usuarioConverter,
                          PasswordEncoder passwordEncoder,
                          RoleService roleService,
                          RoleConverter roleConverter){
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.usuarioConverter = usuarioConverter;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.roleConverter = roleConverter;
    }

    public String autenticarUsuario(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        System.out.println("\n\n\n\n TEEEEEXST \n\n\n\n");
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Usuario ingresado exitosamente";
    }

    public ResponseEntity<String> registrarUsuario(UsuarioDto usuarioDto){
        System.out.println(this.usuarioRepository.findByUsername(usuarioDto.getUsername()));
        if(this.usuarioRepository.findByUsername(usuarioDto.getUsername()) == null){
            return new ResponseEntity<>("El nombre de usuario ya fue tomado", HttpStatus.BAD_REQUEST);
        } else if (this.usuarioRepository.findByEmail(usuarioDto.getEmail()) != null) {
            return new ResponseEntity<>("El email ya esta en uso", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = this.usuarioConverter.toEntity(usuarioDto);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Role role = this.roleConverter.toEntity(this.roleService.getRoleByName("ROLE_ADMIN"));
        usuario.setRoleList(Collections.singleton(role).stream().collect(Collectors.toList()));

        this.usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }

}
