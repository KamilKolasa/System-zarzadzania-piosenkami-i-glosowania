package com.app.security;

import com.app.exception.AppException;
import com.app.model.dto.Info;
import com.app.model.dto.security.AuthenticationUser;
import com.app.model.dto.security.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationUser user = new ObjectMapper().readValue(request.getInputStream(), AuthenticationUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.emptyList()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("attemp authentication exception");
        }

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var jsonData = Info.<Token>builder().data(TokenGenerator.generateTokens(authResult)).build();
        response.getWriter().write(new ObjectMapper().writeValueAsString(jsonData));
        response.getWriter().flush();
        response.getWriter().close();

    }
}
