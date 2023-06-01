package com.example.examen.repositories;

import com.example.examen.entities.Asignatura;
import com.example.examen.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
        Asignatura findByNombre(String nombre);

        Asignatura findAsignaturaById(Long id);

        List<Asignatura> findAll();

        Asignatura findAsignaturaByNombreAndFamiliaProfesional(String nombre, String familiaProfesional);


}