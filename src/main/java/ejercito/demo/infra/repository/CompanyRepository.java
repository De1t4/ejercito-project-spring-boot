package ejercito.demo.infra.repository;

import ejercito.demo.models.Company;
import ejercito.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
  @Query("""
               SELECT c FROM Company c
               WHERE c.id_company LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(c.activity) LIKE LOWER(CONCAT('%', :search, '%'))
          \s""")
  Page<Company> getPageCompaniesFounded(Pageable pageable, String search);
}
