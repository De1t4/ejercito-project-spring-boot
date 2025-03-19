package ejercito.demo.infra.repository;

import ejercito.demo.models.Barrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarrackRepository extends JpaRepository<Barrack, Long> {
}
