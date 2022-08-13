package com.Disney.Alkemy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUsuario;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Role> roleList = new ArrayList<>();

    public Usuario(Long idUsuario,
                   String nombre,
                   String username,
                   String email,
                   String password,
                   List<Role> roleList) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleList = roleList;
    }

    public Usuario(Long idUsuario, String nombre, String username, String email, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }
}
