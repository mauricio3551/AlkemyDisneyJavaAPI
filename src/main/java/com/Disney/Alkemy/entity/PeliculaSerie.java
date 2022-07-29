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
public class PeliculaSerie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPeliculaSerie;
  @NotBlank(message = "Ingrese titulo")
  private String titulo;
  @NotBlank(message = "Ingrese fecha creacion")
  private String fechaCreacion;
  @NotBlank(message = "Ingrese calificacion")
  private int calificacion;
  @NotBlank(message = "Ingresar genero")
  @ManyToOne(fetch = FetchType.EAGER)
  @JsonManagedReference(value = "generoPelicula")
  private Genero genero;
  @NotBlank(message = "Ingresar personajes")
  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Personaje> personajeSet = new HashSet<>();

  public PeliculaSerie(Long idPeliculaSerie, String titulo, String fechaCreacion, int calificacion) {
    this.idPeliculaSerie = idPeliculaSerie;
    this.titulo = titulo;
    this.fechaCreacion = fechaCreacion;
    this.calificacion = calificacion;
  }

  public PeliculaSerie() {
  }
}
