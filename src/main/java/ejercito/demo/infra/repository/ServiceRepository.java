package ejercito.demo.infra.repository;


import ejercito.demo.models.Services;
import ejercito.demo.models.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Long> {



}
