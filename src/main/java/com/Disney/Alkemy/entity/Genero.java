package com.Disney.Alkemy.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Genero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idGenero;
  @NotBlank(message = "Ingrese nombre")
  private String nombre;
  @OneToMany(mappedBy = "genero")
  @JsonBackReference(value = "generoPelicula")
  private Set<PeliculaSerie> peliculaSerieSet = new HashSet<>();

  public Genero(Long idGenero, String nombre) {
    this.idGenero = idGenero;
    this.nombre = nombre;
  }

  public Genero() {
  }
}
