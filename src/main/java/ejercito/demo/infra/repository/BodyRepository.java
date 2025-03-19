package ejercito.demo.infra.repository;

import ejercito.demo.models.Body;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyRepository extends JpaRepository<Body, Long> {
}
