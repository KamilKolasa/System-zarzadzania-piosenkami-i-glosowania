package com.app.service;

import com.app.exception.AppException;
import com.app.model.Role;
import com.app.model.User;
import com.app.model.dto.CreateUserDto;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(CreateUserDto createUserDto) {

        if (createUserDto == null) {
            throw new AppException("USER OBJECT IS NULL");
        }

        if (userRepository.findByUsername(createUserDto.getUsername()).isPresent()) {
            throw new AppException("USERNAME ALREADY EXISTS");
        }

        if (!Objects.equals(createUserDto.getPassword(), createUserDto.getPasswordConfirmation())) {
            throw new AppException("PASSWORD AND CONFIRMED PASSWORD ARE NOT THE SAME");
        }

        if (createUserDto.getRole() == null) {
            createUserDto.setRole(Role.ROLE_USER);
        }

        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        return userRepository.save(Mapper.fromCreateUserDtoToUser(createUserDto));
    }
}
