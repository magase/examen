package com.example.examen.services;

import com.example.examen.entities.Grupo;

import java.util.List;

public interface GrupoService {
    List<Grupo> getAllGrupos();

    List<Grupo> getGrupoByNombre(String nombre);

    void saveGrupo(Grupo departamento);

    Grupo getGrupoById(Long id);

    Grupo updateGrupo(Grupo departamento);

    Grupo findGrupoByNombreAndCurso(String nombre, int curso);

    void deleteGrupoById(Long id);
}
