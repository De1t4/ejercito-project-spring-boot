package ejercito.demo.infra.config;

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

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // Obtener el token del header
    var authHeader = request.getHeader("Authorization"); //authorizamos que se envio algo en header
    if (authHeader != null) {//validamos el header
      var token = authHeader.replace("Bearer ", ""); //replazamos el auth por el bearer
      var nombreUsuario = tokenService.getSubject(token); // extraemos el nombre de usuario
      if (nombreUsuario != null) { //validamos si el usuario existe
        // Token valido
        var user = userRepository.findByUsername(nombreUsuario); //buscamos al usuario respectivamente
        var authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.get().getAuthorities()); // Forzamos un inicio de sesion
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response); //es necesario llamar el otro filtro para continuar
  }
}
