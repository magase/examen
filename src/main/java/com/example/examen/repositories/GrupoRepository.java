package com.example.examen.repositories;

import com.example.examen.entities.Alumno;
import com.example.examen.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Override
    List<Grupo> findAll();

    Grupo findGrupoById(Long id);
    List<Grupo> findGrupoByNombre(String nombre);
    List<Grupo> findGrupoByCurso(String curso);

    Grupo findGrupoByNombreAndCurso(String nombre, int curso);


}
