package com.example.examen.services.impl;

import com.example.examen.entities.Grupo;
import com.example.examen.repositories.GrupoRepository;
import com.example.examen.services.GrupoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GrupoServiceImpl implements GrupoService {

    GrupoRepository grupoRepository;

    public GrupoServiceImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }

    @Override
    public List<Grupo> getGrupoByNombre(String nombre) {
        return grupoRepository.findGrupoByNombre(nombre);
    }

    @Override
    public void saveGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    @Override
    public Grupo getGrupoById(Long id) {
        return grupoRepository.findGrupoById(id);
    }

    @Override
    public Grupo updateGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public Grupo findGrupoByNombreAndCurso(String nombre, int curso) {
        return grupoRepository.findGrupoByNombreAndCurso(nombre, curso);
    }

    @Override
    public void deleteGrupoById(Long id) {
        grupoRepository.deleteById(id);
    }
}
