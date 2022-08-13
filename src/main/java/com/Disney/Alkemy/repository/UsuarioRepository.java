package com.Disney.Alkemy.repository;

import com.Disney.Alkemy.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM USUARIO u WHERE u.EMAIL = ?1", nativeQuery = true)
    Usuario findByEmail(String email);
    Optional<Usuario> findByUsernameOrEmail(String username, String email);
    @Query(value = "SELECT * FROM USUARIO u WHERE u.USERNAME = ?1", nativeQuery = true)
    Usuario findByUsername(String username);
}
