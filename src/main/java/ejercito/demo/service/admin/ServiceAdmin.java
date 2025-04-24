package ejercito.demo.service.admin;

import ejercito.demo.infra.repository.*;
import ejercito.demo.models.*;
import ejercito.demo.service.soldier.DataManageSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    List<Assignment> recentServices = assignmentRepository.findRecentAssignments();
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

  public Page<DataGeneralSoldiersAdmin> getAllSoldiersData(Pageable pageable) {
    Page<User> soldiersList = userRepository.getPageUsersSoldiers(pageable);
    return soldiersList.map(user -> {
      Soldier soldier = user.getSoldier();
      return new DataGeneralSoldiersAdmin(
              user.getId_user(),
              soldier.getId_soldier(),
              user.getUsername(),
              soldier.getName(),
              soldier.getLastname(),
              soldier.getBarrack().getId_barrack(),
              soldier.getBarrack().getName(),
              soldier.getCompany().getId_company(),
              soldier.getCompany().getActivity(),
              soldier.getBody().getId_body(),
              soldier.getBody().getDenomination()
      );
    });
  }

}
