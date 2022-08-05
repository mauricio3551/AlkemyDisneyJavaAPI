package com.Disney.Alkemy.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Personaje {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPersonaje;
  @Column(nullable = false, length = 50)
  private String nombre;
  @Column(nullable = false)
  private int peso;
  @Column(nullable = false)
  private int edad;
  @Column(nullable = true, length = 500)
  private String historia;
  @Column(nullable = true, length = 500)
  private String imagen;

  @ManyToMany(mappedBy = "personajeList")
  @JsonBackReference
  private List<PeliculaSerie> peliculaSerieSet = new ArrayList<>();

  public Personaje(Long idPersonaje, String nombre, int peso, int edad, String historia, String imagen) {
    this.idPersonaje = idPersonaje;
    this.nombre = nombre;
    this.peso = peso;
    this.edad = edad;
    this.historia = historia;
    this.imagen = imagen;
  }

  public Personaje() {
  }
}
