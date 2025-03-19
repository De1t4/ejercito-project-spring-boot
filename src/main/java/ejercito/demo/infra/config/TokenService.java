package ejercito.demo.infra.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ejercito.demo.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.secret}")
  private String API_SECRET;

  public String generarToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
      return JWT.create()
              .withIssuer("ejercito")
              .withSubject(user.getUsername())
              .withClaim("id", user.getId_user())
              .withAudience(user.getRole())
              .withExpiresAt(generarFechaExpiracion())
              .sign(algorithm);
    } catch (JWTCreationException exception){
      throw new RuntimeException();
    }
  }

  public String getSubject(String token) {
    if (token == null) {
      throw new RuntimeException();
    }
    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(API_SECRET); // validando firma
      verifier = JWT.require(algorithm)
              .withIssuer("ejercito")
              .build()
              .verify(token);
      verifier.getSubject();
    } catch (JWTVerificationException exception) {
      System.out.println(exception.toString());
    }
    if (verifier.getSubject() == null) {
      throw new RuntimeException("Verifier invalido");
    }
    return verifier.getSubject();
  }

  private Instant generarFechaExpiracion() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }
}
