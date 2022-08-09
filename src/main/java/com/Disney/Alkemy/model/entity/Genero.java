package com.Disney.Alkemy.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Genero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idGenero;
  @NotBlank(message = "Ingrese nombre")
  private String nombre;

  @OneToMany(mappedBy = "genero")
  @JsonBackReference
  private List<PeliculaSerie> peliculaSerieSet = new ArrayList<>();

  public Genero(Long idGenero, String nombre) {
    this.idGenero = idGenero;
    this.nombre = nombre;
  }

  public Genero() {
  }
}
