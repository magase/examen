package com.example.examen.controller;

import com.example.examen.entities.Grupo;
import com.example.examen.repositories.GrupoRepository;
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
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping({"/grupos", "/grupos/"})
    public String grupos(Model model){
        model.addAttribute("grupos", grupoService.getAllGrupos());
        return "/grupos/grupos";
    }

    @GetMapping({"/grupos/new", "/grupos/new/"})
    public String nuevoGrupo(Model model){
        Grupo grupo = new Grupo();
        model.addAttribute("grupo", grupo);
        return "/grupos/nuevo_grupo";
    }

    @PostMapping("/grupos/new")
    public String saveDepartamento(@Valid @ModelAttribute("grupo") Grupo grupo, BindingResult result, Model model){
        Grupo existing = grupoService.findGrupoByNombreAndCurso(grupo.getNombre(), grupo.getCurso());
        if (existing != null) {
            result.rejectValue("nombre", null, "Este nombre ya esta registrado ");
            result.rejectValue("curso", null, "Este curso ya esta registrado ");
            return "redirect:/grupos/new?exist";

        }
        if (result.hasErrors()){
            model.addAttribute("grupo", grupo);
            return "redirect:/grupos/new?error";
        }

        grupoService.saveGrupo(grupo);
        return "redirect:/grupos?success";
    }

    @GetMapping("/grupos/delete/{id}")
    public String deleteAsignatura(@PathVariable Long id){
        grupoService.deleteGrupoById(id);
        return "redirect:/grupos";
    }
}
