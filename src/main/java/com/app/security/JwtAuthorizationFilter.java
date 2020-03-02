package com.app.security;

import com.app.exception.AppException;
import com.app.service.AuthenticationData;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)  {

        try {
            String accessToken = request.getHeader(SecurityConstants.HEADER_STRING_ACCESS_TOKEN);
            if ( accessToken == null || !accessToken.startsWith(SecurityConstants.TOKEN_PREFIX) ) {
                chain.doFilter(request, response);
                return;
            }
            UsernamePasswordAuthenticationToken authenticationToken = TokenGenerator.parseAccessToken(accessToken);
            AuthenticationData.setAuthenticatedUser(authenticationToken.getName());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("do filter internal exception");
        }
    }
}
