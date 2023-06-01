package com.example.examen.services;

import com.example.examen.dto.UserDto;
import com.example.examen.entities.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findByEmail(String email);
    User findById(Long id);


    //List<UserDto> findAllUsers();
    List<User> findAllUsers();

    //List<User> findAllRoles();

    void deleteUserById(Long id);
}
