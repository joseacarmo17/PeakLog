package org.peaklog.config.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.peaklog.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = header.substring(7);
            String login = jwtService.extractLogin(token);

            var usuario = usuarioRepository.findByLogin(login);

            if (usuario.isPresent()) {
                var auth = new UsernamePasswordAuthenticationToken(
                        login,
                        null,
                        null
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // Si el token es inválido, simplemente no autenticamos
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}