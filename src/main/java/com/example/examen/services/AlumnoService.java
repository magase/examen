package com.example.examen.services;

import com.example.examen.dto.UserDto;
import com.example.examen.entities.Alumno;
import com.example.examen.entities.User;

import java.util.List;

public interface AlumnoService {

    void saveAlumno(Alumno alumno, UserDto userDto);

    List<Alumno> findAllAlumnos();

    List<Alumno> findAlumnosByNombre(String nombre);

    Alumno findAlumnoById(Long id);
    Alumno findAlumnoByEmail(String email);

    void updateAlumno(Alumno alumno, User user);

    void deleteAlumnoById(Long id);
}
