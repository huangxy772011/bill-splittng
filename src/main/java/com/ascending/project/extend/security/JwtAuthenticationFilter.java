package com.ascending.project.extend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private String tokenHeader = "TokenAuthorization";
    private String bear = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 1. Extract token
        String tokenHeader = httpServletRequest.getHeader(this.tokenHeader);
        if(tokenHeader != null && tokenHeader.startsWith(bear)){
            String authToken = tokenHeader.substring(7);        //Bearer plus space
            // 2. verify token
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            // 3. verify token payload whether if username is legit
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken fullyAuthenticated = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(fullyAuthenticated);

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
