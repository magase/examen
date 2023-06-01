package com.example.examen.repositories;

import com.example.examen.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Override
    List<Alumno> findAll();

    Alumno findAlumnoById(Long id);

    Alumno findAlumnoByEmail(String email);

    List<Alumno> findAlumnoByNombre(String nombre);
}
