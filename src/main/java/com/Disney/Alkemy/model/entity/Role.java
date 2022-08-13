package com.Disney.Alkemy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idRole;
    @Column(nullable = false, length = 50)
    private String name;

    public Role(Long idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }

    public Role(){

    }
}
