package com.example.examen.controller;

import com.example.examen.entities.Asignatura;
import com.example.examen.entities.Role;
import com.example.examen.services.AsignaturaService;
import com.example.examen.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping({"/roles", "/roles/"})
    public String roles(Model model){
        List<Role> rolesList = roleService.getAllRoles();

        // Ordenar la lista en función del atributo 'id' de forma ascendente
        Collections.sort(rolesList, Comparator.comparingLong(Role::getId));

        model.addAttribute("roles", rolesList );
        return "/roles/roles";
    }


    @GetMapping({"/roles/new", "/roles/new/"})
    public String nuevoAsignatura(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "/roles/crear_rol";
    }

    @PostMapping("/roles/new")
    public String saveAsignaturas(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model){
        role.setName(role.getName().toUpperCase());
        role.setName("ROLE_" + role.getName());
        Role existing = roleService.getByName(role.getName());
        if (existing != null) {
            result.rejectValue("name", null, "Este nombre ya esta registrado ");
            return "redirect:/roles/new?exist";

        }
        if (result.hasErrors()){
            model.addAttribute("role", role);
            //return "crear_empleado";
            return "redirect:/roles/new?error";
        }

        roleService.saveRole(role);
        //return "redirect:/departamentos";
        return "redirect:/roles?success";
    }

    @GetMapping("/roles/delete/{id}")
    public String deleteAsignatura(@PathVariable Long id){
        roleService.deleteRoleById(id);
        return "redirect:/roles";
    }
}
