package org.peaklog.config.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.peaklog.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = header.substring(7);
      String login = jwtService.extractLogin(token);

      var usuario = userRepository.findByLogin(login);

      if (usuario.isPresent()) {
        var auth = new UsernamePasswordAuthenticationToken(login, null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }

    } catch (Exception e) {
      // Si el token es inválido, simplemente no autenticamos
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);
  }
}
