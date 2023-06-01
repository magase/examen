package com.example.examen.repositories;

import com.example.examen.entities.Role;
import com.example.examen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository  extends JpaRepository<Role, Long> {
        Role findByName(String name);
        List<Role> findAll();
}