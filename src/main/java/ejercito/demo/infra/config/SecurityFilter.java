package ejercito.demo.infra.config;

import ejercito.demo.infra.errors.AuthenticateFilterException;
import ejercito.demo.infra.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticateFilterException, AccessDeniedException{
    try {
      // Obtener el token del header
      var authHeader = request.getHeader("Authorization");

      if (authHeader == null || authHeader.isEmpty()) {
        throw new AccessDeniedException("Token de autenticación es requerido");
      }

      var token = authHeader.replace("Bearer ", "");
      var nombreUsuario = tokenService.getSubject(token);
      if (nombreUsuario != null) {
        var user = userRepository.findByUsername(nombreUsuario);
        if (user.isPresent()) {
          var authentication = new UsernamePasswordAuthenticationToken(user.get(), null,
                  user.get().getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      filterChain.doFilter(request, response);
    } catch (AccessDeniedException ex) {
      throw new AccessDeniedException(ex.getMessage());
    }
  }
}
