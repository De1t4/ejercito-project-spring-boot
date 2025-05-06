package ejercito.demo.service.admin;

import ejercito.demo.infra.repository.*;
import ejercito.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAdmin {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BarrackRepository barrackRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private BodyRepository bodyRepository;

  @Autowired
  private AssignmentRepository assignmentRepository;

  public DataGeneralAdmin getDataGeneralSystem() {
    int totalSoldier = userRepository.getListUsersSoldiers().size();
    int totalServices = assignmentRepository.findAll().size();
    int servicesCompleted = assignmentRepository.getAssignmentsCompleted().size();
    int servicesPending = assignmentRepository.getAssignmentsPending().size();
    LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
    List<Assignment> recentServices = assignmentRepository.findRecentAssignments(sevenDaysAgo);
    List<DataUserAdmin> dataUserAdminList = new ArrayList<>();

    for (Assignment assignment : recentServices) {
      dataUserAdminList.add(new DataUserAdmin(assignment.getId_services_soldiers(), assignment.getSoldier().getName().concat(" " + assignment.getSoldier().getLastname()), assignment.getServices().getDescription(), assignment.getAt_service()));
    }

    return new DataGeneralAdmin(totalSoldier, totalServices, servicesCompleted, servicesPending, new ArrayList<DataUserAdmin>(dataUserAdminList));
  }

  public DataStructureAdmin getDataStructure() {
    List<Barrack> barracks = barrackRepository.findAll();
    List<Company> companies = companyRepository.findAll();
    List<Body> bodies = bodyRepository.findAll();
    return new DataStructureAdmin(companies, bodies, barracks);
  }

  public Page<DataGeneralSoldiersAdmin> getAllSoldiersData(Pageable pageable, String search) {
    if (search != null) {
      Page<User> soldierListFounded = userRepository.getPageSoldiersFounded(pageable, search);
      return mapDataGeneralSoldier(soldierListFounded);
    }
    Page<User> soldiersList = userRepository.getPageUsersSoldiers(pageable);
    return mapDataGeneralSoldier(soldiersList);
  }

  public Page<DataGeneralSoldiersAdmin> mapDataGeneralSoldier(Page<User> soldiersList) {
    return soldiersList.map(user -> {
      Soldier soldier = user.getSoldier();
      return
              new DataGeneralSoldiersAdmin(
                      user.getId_user(),
                      soldier.getId_soldier(),
                      user.getUsername(),
                      soldier.getName(),
                      soldier.getLastname(),
                      soldier.getBarrack() == null ? null : soldier.getBarrack().getId_barrack(),
                      soldier.getBarrack() == null ? null : soldier.getBarrack().getName(),
                      soldier.getCompany() == null ? null : soldier.getCompany().getId_company(),
                      soldier.getCompany() == null ? null : soldier.getCompany().getActivity(),
                      soldier.getBody() == null ? null : soldier.getBody().getId_body(),
                      soldier.getBody() == null ? null : soldier.getBody().getDenomination()
              );
    });
  }


}
