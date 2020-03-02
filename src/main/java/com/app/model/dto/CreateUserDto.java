package com.app.model.dto;

import com.app.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String email;
    private Integer age;
    private Role role;
}
