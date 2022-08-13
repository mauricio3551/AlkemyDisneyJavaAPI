package com.Disney.Alkemy.model.dto;

import com.Disney.Alkemy.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UsuarioDto {

    private Long idUsuario;
    private String nombre;
    private String username;
    private String email;
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> idRoleList = new ArrayList<>();
    private List<RoleDto> roleList = new ArrayList<>();

    public UsuarioDto(Long idUsuario, String nombre, String username, String email, List<Role> roleList) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.roleList = tomarRole(roleList);
    }

    public UsuarioDto(Long idUsuario, String nombre, String username, String email, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UsuarioDto(Long idUsuario,
                      String nombre,
                      String username,
                      String email,
                      String password,
                      List<Long> idRoleList) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.idRoleList = idRoleList;
    }

    public UsuarioDto() {
    }

    private List<RoleDto> tomarRole(List<Role> r){
        return r.stream().map(role -> new RoleDto(
                role.getIdRole(),
                role.getName()
        )).collect(Collectors.toList());
    }
}
