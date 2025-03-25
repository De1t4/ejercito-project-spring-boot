package ejercito.demo.infra.config;

import ejercito.demo.infra.errors.AuthenticateFilterException;
import ejercito.demo.infra.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(
          @NotNull HttpServletRequest request,
          @NotNull HttpServletResponse response,
          @NotNull FilterChain filterChain) throws ServletException, IOException, AuthenticateFilterException, AccessDeniedException{

    if (request.getServletPath().contains("/auth/**") || request.getServletPath().contains("/swagger-ui/**") || request.getServletPath().contains("/v3/**") ) {
      filterChain.doFilter(request, response);
      return;
    }
    var authHeader = request.getHeader("Authorization");

      if(authHeader != null){
        var token = authHeader.replace("Bearer ", "");

        var nombreUsuario = tokenService.getSubject(token);
        if (nombreUsuario != null) {
          var user = userRepository.findByUsername(nombreUsuario);
          if (user.isPresent()) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.get().getRole()));
            var authentication = new UsernamePasswordAuthenticationToken(user.get(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
      }
      filterChain.doFilter(request, response);
  }
}
