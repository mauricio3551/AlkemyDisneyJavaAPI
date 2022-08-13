package com.Disney.Alkemy.model.converter;

import com.Disney.Alkemy.model.dto.RoleDto;
import com.Disney.Alkemy.model.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDto toDto(Role role){
        return new RoleDto(
                role.getIdRole(),
                role.getName()
        );
    }

    public Role toEntity(RoleDto roleDto){
        return new Role(
                roleDto.getIdRole(),
                roleDto.getName()
        );
    }
}
