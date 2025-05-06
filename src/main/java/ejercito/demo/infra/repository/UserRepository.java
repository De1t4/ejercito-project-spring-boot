package ejercito.demo.infra.repository;

import ejercito.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SOLDADO' and u.soldier.id_soldier = :idSoldier")
  User findUserSoldierById(Long idSoldier);

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SOLDADO'")
  List<User> getListUsersSoldiers();

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SOLDADO'")
  Page<User> getPageUsersSoldiers(Pageable pageable);

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SUB_OFICIAL'")
  Page<User> getPageUsersSubOficials(Pageable pageable);

  @Query("SELECT u FROM User u \n" +
          " where u.role = 'SUB_OFICIAL' and u.id_user = :id")
  User findSubOfficialById(Long id);

  @Query("""
               SELECT u FROM User u
               WHERE u.role = 'SOLDADO' and ( LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR u.id_user LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(CONCAT(u.soldier.name, ' ' ,  u.soldier.lastname)) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.name) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.lastname) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.company.activity) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.barrack.name) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.body.denomination) LIKE LOWER(CONCAT('%', :search, '%')))
          \s""")
  Page<User> getPageSoldiersFounded(Pageable pageable, String search);

  @Query("""
               SELECT u FROM User u
               WHERE u.role = 'SUB_OFICIAL' and ( LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR u.id_user LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(CONCAT(u.soldier.name, ' ' ,  u.soldier.lastname)) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.name) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(u.soldier.lastname) LIKE LOWER(CONCAT('%', :search, '%')))
          \s""")
  Page<User> getPageSubOfficialsFounded(Pageable pageable, String search);
}
