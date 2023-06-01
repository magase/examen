package com.example.examen.repositories;

import com.example.examen.entities.User;
import com.example.examen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findUserById(Long id);

    User findUserByEmail(String email);

    List<User> findUserByName(String name);
}
