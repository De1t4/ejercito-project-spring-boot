package ejercito.demo.infra.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class WebSecurityConfig {


  @Autowired
  private SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register", "/auth/**").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/v1/services/finish/assignments").hasAnyRole("SOLDADO", "SUB_OFICIAL", "OFICIAL")
                    .requestMatchers(HttpMethod.GET, "/v1/**").hasAnyRole("SOLDADO", "SUB_OFICIAL", "OFICIAL")
                    .requestMatchers(HttpMethod.PUT, "/v1/profile/update").hasAnyRole("SUB_OFICIAL", "OFICIAL", "SOLDADO")
                    .requestMatchers(HttpMethod.POST, "/v1/**").hasAnyRole("SUB_OFICIAL", "OFICIAL")
                    .requestMatchers(HttpMethod.PUT, "/v1/**").hasAnyRole("SUB_OFICIAL", "OFICIAL")
                    .requestMatchers(HttpMethod.DELETE, "/v1/**").hasAnyRole("SUB_OFICIAL", "OFICIAL")
                    .requestMatchers("/swagger-ui/**", "/swagger-ui.html" ,"/v3/api-docs/**", "/swagger-resources/**", "/error").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

//  @Bean
//  public AuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//    return authenticationConfiguration.getAuthenticationManager();
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:3000", "http://localhost:5173"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}