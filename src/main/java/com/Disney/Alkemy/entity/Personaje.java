package com.Disney.Alkemy.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Personaje {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPersonaje;
  @NotBlank(message = "Ingrese nombre")
  private String nombre;
  @NotBlank(message = "Ingrese peso")
  private int peso;
  @NotBlank(message = "Ingrese historia")
  private String historia;
  @ManyToMany(mappedBy = "setPersonaje")
  private Set<PeliculaSerie> peliculaSerieSet = new HashSet<>();

  public Personaje(Long idPersonaje, String nombre, int peso, String historia) {
    this.idPersonaje = idPersonaje;
    this.nombre = nombre;
    this.peso = peso;
    this.historia = historia;
  }

  public Personaje() {
  }
}
