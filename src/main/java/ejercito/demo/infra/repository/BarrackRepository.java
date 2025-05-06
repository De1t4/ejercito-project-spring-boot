package ejercito.demo.infra.repository;

import ejercito.demo.models.Barrack;
import ejercito.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BarrackRepository extends JpaRepository<Barrack, Long> {

  @Query("""
               SELECT b FROM Barrack b
               WHERE b.id_barrack LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(CONCAT(b.name, ' ' ,  b.location)) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(b.location) LIKE LOWER(CONCAT('%', :search, '%'))
          \s""")
  Page<Barrack> getPageBarracksFounded(Pageable pageable, String search);
}
