package com.example.examen.services.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.examen.dto.UserDto;
import com.example.examen.entities.Alumno;
import com.example.examen.entities.Role;
import com.example.examen.entities.User;
import com.example.examen.repositories.*;
import com.example.examen.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {


    private AlumnoRepository alumnoRepository;
    private AsignaturaRepository asignaturaRepository;

    private GrupoRepository grupoRepository;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, AsignaturaRepository asignaturaRepository, GrupoRepository grupoRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.alumnoRepository = alumnoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.grupoRepository = grupoRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAlumno(Alumno alumno, UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());


        if (userDto.getRoles().isEmpty()){
            Role role;
            role = checkRoleExist();
            user.setRoles(List.of(role));

        }

        alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
        userRepository.save(user);
        alumnoRepository.save(alumno);

    }

    @Override
    public List<Alumno> findAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public List<Alumno> findAlumnosByNombre(String nombre) {
        return alumnoRepository.findAlumnoByNombre(nombre);
    }

    @Override
    public Alumno findAlumnoById(Long id) {
        return alumnoRepository.findAlumnoById(id);
    }

    @Override
    public Alumno findAlumnoByEmail(String email) {
        return alumnoRepository.findAlumnoByEmail(email);
    }

    @Override
    public void updateAlumno(Alumno alumno, User user) {
        userRepository.save(user);
        alumnoRepository.save(alumno);
    }

    @Override
    public void deleteAlumnoById(Long id) {
        alumnoRepository.deleteById(id);
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
