package com.example.examen.services.impl;

import com.example.examen.entities.Asignatura;
import com.example.examen.repositories.AsignaturaRepository;
import com.example.examen.services.AsignaturaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    AsignaturaRepository asignaturaRepository;

    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public void saveAsignatura(Asignatura asignatura) {
        asignaturaRepository.save(asignatura);
    }

    @Override
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Override
    public Asignatura getAsignaturaById(Long id) {
        return asignaturaRepository.findAsignaturaById(id);
    }

    @Override
    public Asignatura getAsignaturaByNombre(String nombre) {
        return asignaturaRepository.findByNombre(nombre);
    }

    @Override
    public Asignatura getAsignaturaByNombreAndFamiliaProfesional(String nombre, String familiaProfesional) {
        return asignaturaRepository.findAsignaturaByNombreAndFamiliaProfesional(nombre, familiaProfesional);
    }

    @Override
    public void deleteAsignaturaById(Long id) {
        asignaturaRepository.deleteById(id);
    }
}
