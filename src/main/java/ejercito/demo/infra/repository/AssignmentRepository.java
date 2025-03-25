package ejercito.demo.infra.repository;

import ejercito.demo.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long > {

  @Query("SELECT a FROM Assignment a WHERE a.soldier.id_soldier = :soldierId")
  List<Assignment> findBySoldierId(@Param("soldierId") Long soldierId);
}
