package com.company.bookstoreapp.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.company.bookstoreapp.constants.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final SecurityConstants securityConstants;
    private final JWTProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (StringUtils.isNotBlank(authHeader)) {
            if (authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);
                if (StringUtils.isBlank(jwtToken)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad bearer authentication");
                }
                if (jwtToken.length() > 30) {
                    try {
                        String userId = jwtProvider.extractUserId(jwtToken);
                        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
                        if (SecurityContextHolder.getContext().getAuthentication() == null) {
                            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(token);
                        }
                    } catch (JWTVerificationException ex) {

                      return;
                    }
                }
            }

        }

        doFilter(request, response, filterChain);

    }
}
