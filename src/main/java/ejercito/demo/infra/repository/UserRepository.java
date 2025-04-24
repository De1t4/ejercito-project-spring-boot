package ejercito.demo.infra.repository;

import ejercito.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SOLDADO'")
  List<User> getListUsersSoldiers();

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SOLDADO'")
  Page<User> getPageUsersSoldiers(Pageable pageable);

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SOLDADO'")
  Page<User> findByUsernameOrCompany(Pageable pageable, String username);
}
