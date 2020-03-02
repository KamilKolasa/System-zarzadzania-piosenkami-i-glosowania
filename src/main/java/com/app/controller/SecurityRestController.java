package com.app.controller;

import com.app.model.dto.CreateUserDto;
import com.app.model.dto.Info;
import com.app.model.dto.security.Token;
import com.app.security.TokenGenerator;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityRestController {

    private final UserService userService;

    @PostMapping("/registration")
    public Info<String> registerNewUser(@RequestBody CreateUserDto userDto) {
        var registeredUser = userService.registerUser(userDto);
        return Info.<String>builder().data("user " + registeredUser.getUsername() + " registered").build();
    }

    @PostMapping("/refreshToken")
    public Info<Token> refreshToken(@RequestParam String token) {
        return Info.<Token>builder().data(TokenGenerator.generateTokensFromRefreshToken(token)).build();
    }
}
