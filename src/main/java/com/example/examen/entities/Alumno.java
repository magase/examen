package com.example.examen.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
public class Alumno {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    private String nombre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Column(nullable=false, unique=true)
    @NotEmpty(message = "No puede estar vacio")
    @Email
    private String email;

    @NotEmpty(message = "No puede estar vacio")
    private String password;

    private Float altura;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo")
    Grupo grupo;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name="alumnos_asignaturas",
            joinColumns={@JoinColumn(name="ALUMNO_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ASIGNATURA_ID", referencedColumnName="ID")})
    private List<Asignatura> asignaturas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name="alumnos_roles",
            joinColumns={@JoinColumn(name="ALUMNO_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        asignaturas.clear(); // Elimina las referencias a los roles para evitar la restricción de clave externa
        roles.clear(); // Elimina las referencias a los roles para evitar la restricción de clave externa
    }


}
