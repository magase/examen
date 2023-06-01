package com.example.examen.services;

import com.example.examen.entities.Role;

import java.util.List;

public interface RoleService {

    Role saveRole(Role role);

    Role getByName(String name);

    List<Role> getAllRoles();

    void deleteRoleById(Long id);
}
