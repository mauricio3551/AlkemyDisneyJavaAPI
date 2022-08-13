package com.Disney.Alkemy.service;

import com.Disney.Alkemy.model.entity.Role;
import com.Disney.Alkemy.model.entity.Usuario;
import com.Disney.Alkemy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallesUsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;


    @Autowired
    public DetallesUsuarioService(UsuarioRepository usuarioRepositor){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario" + usernameOrEmail + "no encontrado"));

        return new User(usuario.getEmail(), usuario.getPassword(), mapRolesToAuthorities(usuario.getRoleList()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roleList){
        return roleList.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
