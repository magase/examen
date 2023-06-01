package com.example.examen.controller;

import com.example.examen.entities.Asignatura;
import com.example.examen.entities.Grupo;
import com.example.examen.services.AsignaturaService;
import com.example.examen.services.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping({"/asignaturas", "/asignaturas/"})
    public String asignaturas(Model model){
        model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
        return "/asignaturas/asignaturas";
    }


    @GetMapping({"/asignaturas/new", "/asignaturas/new/"})
    public String nuevoAsignatura(Model model){
        Asignatura asignatura = new Asignatura();
        model.addAttribute("asignatura", asignatura);
        return "/asignaturas/nueva_asignatura";
    }

    @PostMapping("/asignaturas/new")
    public String saveAsignaturas(@Valid @ModelAttribute("asignatura") Asignatura asignatura, BindingResult result, Model model){
        Asignatura existing = asignaturaService.getAsignaturaByNombreAndFamiliaProfesional(asignatura.getNombre(), asignatura.getFamiliaProfesional());
        if (existing != null) {
            result.rejectValue("nombre", null, "Este nombre ya esta registrado ");
            result.rejectValue("familiaProfesional", null, "Esta familia ya esta registrada ");
            return "redirect:/asignaturas/new?exist";

        }
        if (result.hasErrors()){
            model.addAttribute("asignatura", asignatura);
            return "redirect:/asignaturas/new?error";
        }

        asignaturaService.saveAsignatura(asignatura);
        return "redirect:/asignaturas?success";
    }

    @GetMapping("/asignaturas/delete/{id}")
    public String deleteAsignatura(@PathVariable Long id){
        asignaturaService.deleteAsignaturaById(id);
        return "redirect:/asignaturas";
    }
}
