package com.Disney.Alkemy.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class PeliculaSerie {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long idPeliculaSerie;
  @Column(nullable = false, length = 50)
  private String titulo;
  @Column(nullable = false)
  @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date fechaCreacion;
  @Column(length = 500)
  private String imagen;
  @Column(nullable = false)
  private int calificacion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonBackReference
  private Genero genero;

  @ManyToMany(fetch = FetchType.EAGER)
  @JsonBackReference
  private List<Personaje> personajeList = new ArrayList<>();

  public PeliculaSerie(Long idPeliculaSerie, String titulo, Date fechaCreacion, String imagen, int calificacion) {
    this.idPeliculaSerie = idPeliculaSerie;
    this.titulo = titulo;
    this.fechaCreacion = fechaCreacion;
    this.imagen = imagen;
    this.calificacion = calificacion;
  }

  public PeliculaSerie(Long idPeliculaSerie,
                       String titulo,
                       Date fechaCreacion,
                       String imagen,
                       int calificacion,
                       Genero genero,
                       List<Personaje> personajeList) {
    this.idPeliculaSerie = idPeliculaSerie;
    this.titulo = titulo;
    this.fechaCreacion = fechaCreacion;
    this.imagen = imagen;
    this.calificacion = calificacion;
    this.genero = genero;
    this.personajeList = personajeList;
  }

  public PeliculaSerie() {
  }
}
