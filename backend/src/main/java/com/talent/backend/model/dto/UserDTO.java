package com.talent.backend.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String email;
    private String name;
    private String lastName;
    private String city;
    private String password;
    private Set<String> roles;
}
