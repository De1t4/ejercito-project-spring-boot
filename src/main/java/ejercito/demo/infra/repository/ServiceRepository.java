package ejercito.demo.infra.repository;


import ejercito.demo.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
