package ejercito.demo.infra.repository;

import ejercito.demo.models.Body;
import ejercito.demo.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyRepository extends JpaRepository<Body, Long> {
  @Query("""
               SELECT b FROM Body b
               WHERE b.id_body LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(b.denomination) LIKE LOWER(CONCAT('%', :search, '%'))
          \s""")
  Page<Body> getPageBodiesFounded(Pageable pageable, String search);
}
