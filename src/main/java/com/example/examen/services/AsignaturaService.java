package com.example.examen.services;

import com.example.examen.entities.Asignatura;
import com.example.examen.entities.Role;

import java.util.List;

public interface AsignaturaService {

    void saveAsignatura(Asignatura asignatura);
    List<Asignatura> getAllAsignaturas();

    Asignatura getAsignaturaById(Long id);

    Asignatura getAsignaturaByNombre(String nombre);
    Asignatura getAsignaturaByNombreAndFamiliaProfesional(String nombre, String familiaProfesional);

    void deleteAsignaturaById(Long id);
}
