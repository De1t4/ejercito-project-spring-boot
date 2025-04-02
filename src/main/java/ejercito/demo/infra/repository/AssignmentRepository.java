package ejercito.demo.infra.repository;

import ejercito.demo.models.Assignment;
import ejercito.demo.models.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long > {

  @Query("SELECT a FROM Assignment a WHERE a.soldier.id_soldier = :soldierId")
  List<Assignment> findBySoldierId(@Param("soldierId") Long soldierId);

  @Query("SELECT s FROM Assignment s WHERE s.id_services_soldiers IN :ids")
  List<Assignment> getAssignmentsByIds(@Param("ids") List<Long> ids);
}
