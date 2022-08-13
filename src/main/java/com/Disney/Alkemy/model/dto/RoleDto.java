package com.Disney.Alkemy.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    private Long idRole;
    private String name;

    public RoleDto(Long idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }

    public RoleDto() {
    }
}
