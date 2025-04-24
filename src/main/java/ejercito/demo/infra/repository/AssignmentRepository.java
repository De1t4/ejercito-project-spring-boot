package ejercito.demo.infra.repository;

import ejercito.demo.models.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

  @Query("SELECT a FROM Assignment a WHERE a.soldier.id_soldier = :soldierId")
  List<Assignment> findBySoldierId(@Param("soldierId") Long soldierId);

  @Query("SELECT s FROM Assignment s WHERE s.id_services_soldiers IN :ids")
  List<Assignment> getAssignmentsByIds(@Param("ids") List<Long> ids);

  @Query("SELECT s FROM Assignment s where s.end_service is null")
  List<Assignment> getAssignmentsPending();

  @Query("SELECT s FROM Assignment s where s.end_service is not null")
  List<Assignment> getAssignmentsCompleted();

  @Query("SELECT s FROM Assignment s WHERE s.at_service >= DATE(CURRENT_DATE - 7)")
  List<Assignment> findRecentAssignments();

  @Query("""
              SELECT a FROM Assignment a
              WHERE LOWER(a.soldier.name) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR LOWER(a.soldier.lastname) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR a.id_services_soldiers LIKE LOWER(CONCAT('%', :search, '%'))
                 OR a.at_service LIKE LOWER(CONCAT('%', :search, '%'))
                 OR a.end_service LIKE LOWER(CONCAT('%', :search, '%'))
          """)
  Page<Assignment> findAllAssignments(Pageable pageable, String search);
}
