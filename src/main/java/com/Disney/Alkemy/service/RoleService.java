package com.Disney.Alkemy.service;

import com.Disney.Alkemy.model.converter.RoleConverter;
import com.Disney.Alkemy.model.dto.RoleDto;
import com.Disney.Alkemy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private RoleConverter roleConverter;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleConverter roleConverter){
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    public RoleDto getRoleByName(String nombre){
        return this.roleConverter.toDto(this.roleRepository.findByName(nombre).get());
    }
}
