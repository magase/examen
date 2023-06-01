package com.example.examen.controller;

import com.example.examen.dto.UserDto;
import com.example.examen.entities.Alumno;
import com.example.examen.entities.User;
import com.example.examen.repositories.AlumnoRepository;
import com.example.examen.services.*;
import jakarta.validation.Valid;
import org.aspectj.lang.reflect.AdviceSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    AsignaturaService asignaturaService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    Authentication auth;

    public AlumnoController(AlumnoService alumnoService, AlumnoRepository alumnoRepository, AsignaturaService asignaturaService, GrupoService grupoService, UserService userService, RoleService roleService) {
        this.alumnoService = alumnoService;
        this.alumnoRepository = alumnoRepository;
        this.asignaturaService = asignaturaService;
        this.grupoService = grupoService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping({"/alumnos", "/alumnos/"})
    public String alumnos(Model model){
        model.addAttribute("alumnos", alumnoService.findAllAlumnos());

        auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth.getName());
        return "/alumnos/alumnos";
    }


    @GetMapping("/alumnos/new")
    public String crearAlumnoForm(Model model){
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        model.addAttribute("grupos", grupoService.getAllGrupos());
        model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
        model.addAttribute("roleList", roleService.getAllRoles());

        return "/alumnos/crear_alumno";
    }


    @PostMapping("/alumnos/new")
    public String saveAlumno(@Valid @ModelAttribute("alumno") Alumno alumno, BindingResult result, Model model){
        Alumno existing = alumnoService.findAlumnoByEmail(alumno.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Este email ya esta registrado con una cuenta");
            return "/alumnos/crear_alumno";

        }
        if (result.hasErrors()){
            model.addAttribute("alumno", alumno);
            //return "crear_alumno";
            return "redirect:/alumnos/new?error";
        }if (alumno.getRoles().isEmpty()){
            result.rejectValue("roles", null, "El rol no puede estar vacio");
            model.addAttribute("alumno", alumno);
            model.addAttribute("grupos", grupoService.getAllGrupos());
            model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
            model.addAttribute("roleList", roleService.getAllRoles());
            return "/alumnos/crear_alumno";
        }if (alumno.getGrupo() == null){
            result.rejectValue("grupos", null, "El grupos no puede estar vacio");
            model.addAttribute("alumno", alumno);
            model.addAttribute("grupos", grupoService.getAllGrupos());
            model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
            model.addAttribute("roleList", roleService.getAllRoles());
            return "/alumnos/crear_alumno";
        }if (alumno.getAsignaturas() == null){
            result.rejectValue("asignaturas", null, "El grupos no puede estar vacio");
            model.addAttribute("alumno", alumno);
            model.addAttribute("grupos", grupoService.getAllGrupos());
            model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
            model.addAttribute("roleList", roleService.getAllRoles());
            return "/alumnos/crear_alumno";
        }if (alumno.getAltura()<1 || alumno.getAltura()>2.50 || alumno.getAltura()==null){
            result.rejectValue("altura", null, "La altura no puede así");
            model.addAttribute("alumno", alumno);
            model.addAttribute("grupos", grupoService.getAllGrupos());
            model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
            model.addAttribute("roleList", roleService.getAllRoles());
            return "/alumnos/crear_alumno";
        }
        UserDto userDto= new UserDto();
        userDto.setName(alumno.getNombre());
        userDto.setPassword(alumno.getPassword());
        userDto.setEmail(alumno.getEmail());
        userDto.setRoles(alumno.getRoles());

        alumnoService.saveAlumno(alumno, userDto);

        //return "redirect:/alumnos";
        //return "redirect:/alumnos?success";
        return "redirect:/alumnos/new?success";
    }

    @GetMapping("/alumnos/edit/{id}")
    public String editAlumnoForm(@PathVariable Long id, Model model){
        Alumno alumn = alumnoService.findAlumnoById(id);
        model.addAttribute("alumno", alumn);
        model.addAttribute("grupos", grupoService.getAllGrupos());
        model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
        model.addAttribute("roleList", roleService.getAllRoles());
        return "/alumnos/actualizar_alumno";
    }

    @PostMapping("/alumnos/update/{id}")
    public String updateAlumno(@PathVariable Long id, @ModelAttribute("alumno") Alumno alumno, Model model){
        Alumno existAlumno = alumnoService.findAlumnoById(id);
        existAlumno.setId(id);
        existAlumno.setNombre(alumno.getNombre());
        existAlumno.setGrupo(alumno.getGrupo());
        existAlumno.setAsignaturas(alumno.getAsignaturas());
        existAlumno.setEmail(alumno.getEmail());
        existAlumno.setFechaNacimiento(alumno.getFechaNacimiento());
        existAlumno.setAltura(alumno.getAltura());

        User userExist = userService.findById(id);
        userExist.setName(alumno.getNombre());
        userExist.setEmail(alumno.getEmail());
        userExist.setRoles(alumno.getRoles());

        alumnoService.updateAlumno(existAlumno, userExist);
        return "redirect:/alumnos?update";
    }


    @GetMapping("/alumnos/delete/{id}")
    public String deleteAlumno(@PathVariable Long id){
        alumnoService.deleteAlumnoById(id);
        userService.deleteUserById(id);
        return "redirect:/alumnos";
    }



}
