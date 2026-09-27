package com.merkit.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.merkit.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (path.equals("/auth/login")
                || path.equals("/auth/register")
                || path.equals("/auth/refresh-token")
                || path.equals("/auth/logout")
                || path.equals("/auth/forgot")
                || path.equals("/auth/forgot-password")
                || path.equals("/forgot/reset")
                || path.equals("/forgot/change")
                || path.equals("/forgot/secure")
                || path.equals("/error")) {

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String username = jwtUtil.extractUsername(token);
            if (username == null || username.isBlank()) {
                filterChain.doFilter(request, response);
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            UserDetails userDetails =userDetailsService.loadUserByUsername(username);
            if (userDetails == null) {
                filterChain.doFilter(request, response);
                return;
            }

            boolean validToken =jwtUtil.validateToken(token,userDetails );
            if (!validToken) {
                filterChain.doFilter(request, response);
                return;
            }
            
            User user = ((CustomUserDetails) userDetails).getUser();

            if (user == null) {
                filterChain.doFilter(request, response);
                return;
            }

            Long tokenVersion =jwtUtil.extractTokenVersion(token);
            Long currentTokenVersion =user.getTokenVersion();

            if (tokenVersion == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (currentTokenVersion == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!tokenVersion.equals(currentTokenVersion)) {
                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities() );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
//            e.printStackTrace();
            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);
    }
}