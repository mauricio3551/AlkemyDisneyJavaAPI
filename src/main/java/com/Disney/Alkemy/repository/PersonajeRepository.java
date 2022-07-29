package com.Disney.Alkemy.repository;

import com.Disney.Alkemy.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}
