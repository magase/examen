package com.example.examen.dto;

import com.example.examen.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "Nombre no debe estar vacío")
    private String name;
    @NotEmpty(message = "Email no debe estar vacío")
    @Email
    private String email;
    @NotEmpty(message = "Password no debe estar vacío")
    private String password;
    private List<Role> roles = new ArrayList<>();
    //private Role rol;

}
