package com.Disney.Alkemy.model.converter;

import com.Disney.Alkemy.model.dto.UsuarioDto;
import com.Disney.Alkemy.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public UsuarioDto toDto(Usuario usuario){
        return new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRoleList()
        );
    }

    public Usuario toEntity(UsuarioDto usuarioDto){
        return new Usuario(
                usuarioDto.getIdUsuario(),
                usuarioDto.getNombre(),
                usuarioDto.getUsername(),
                usuarioDto.getEmail(),
                usuarioDto.getPassword()
        );
    }
}
