package ejercito.demo.infra.repository;

import ejercito.demo.models.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoldierRepository extends JpaRepository<Soldier, Long> {
  Soldier findByName(String name);

  @Query("SELECT s FROM Soldier s WHERE s.id IN :ids")
  List<Soldier> getSoldiersByIds(@Param("ids") List<Long> ids);
}
