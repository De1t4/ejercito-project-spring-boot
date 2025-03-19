package ejercito.demo.infra.repository;

import ejercito.demo.models.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldierRepository extends JpaRepository<Soldier, Long> {
  Soldier findByName(String name);
}
