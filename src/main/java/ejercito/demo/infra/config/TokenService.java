package ejercito.demo.infra.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import ejercito.demo.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

  @Value("${api.security.secret}")
  private String API_SECRET;

  public String generarToken(User user)  {
    try {
      Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
      return JWT.create()
              .withIssuer("ejercito")
              .withSubject(user.getUsername())
              .withClaim("id", user.getId_user())
              .withClaim("role", user.getRole())
              .withExpiresAt(generarFechaExpiracion())
              .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException();
    }
  }

  public String getSubject(String token) {
    if (token == null || token.trim().isEmpty()) {
      throw new IllegalArgumentException("El token no puede ser nulo o vacío");
    }

    DecodedJWT decodedJWT = JWT.decode(token);

    if (isJWTExpired(decodedJWT)) {
      throw new TokenExpiredException("Token expired", decodedJWT.getExpiresAt().toInstant());
    }

    Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
    JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("ejercito")
            .build();

    DecodedJWT verifiedJWT = verifier.verify(token);
    String subject = verifiedJWT.getSubject();

    if (subject == null || subject.isEmpty()) {
      throw new IllegalStateException("El token no contiene un sujeto válido");
    }

    return subject;
  }

  boolean isJWTExpired(DecodedJWT decodedJWT) {
    Date expiresAt = decodedJWT.getExpiresAt();
    return expiresAt.before(new Date());
  }

  private Instant generarFechaExpiracion() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }
}
