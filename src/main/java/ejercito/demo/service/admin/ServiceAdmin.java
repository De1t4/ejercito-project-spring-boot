package ejercito.demo.service.admin;

import ejercito.demo.infra.repository.AssignmentRepository;
import ejercito.demo.infra.repository.ServiceRepository;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.models.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAdmin {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private AssignmentRepository assignmentRepository;

  public DataGeneralAdmin getDataGeneralSystem(){
    int totalSoldier = soldierRepository.findAll().size();
    int totalServices = assignmentRepository.findAll().size();
    int servicesCompleted = assignmentRepository.getAssignmentsCompleted().size();
    int servicesPending = assignmentRepository.getAssignmentsPending().size();
    List<Assignment> recentServices = assignmentRepository.findRecentAssignments();
    List<DataUserAdmin> dataUserAdminList = new ArrayList<>();

    LocalDate hoy = LocalDate.now();

    // Extrae la parte de la fecha del LocalDateTime recibido

    // Calcula la diferencia en días utilizando ChronoUnit

    for(Assignment assignment: recentServices){
      dataUserAdminList.add(new DataUserAdmin(assignment.getId_services_soldiers(), assignment.getSoldier().getName().concat(" "+ assignment.getSoldier().getLastname()), assignment.getServices().getDescription(), assignment.getAt_service()));
    }

    return new DataGeneralAdmin(totalSoldier, totalServices, servicesCompleted, servicesPending, new ArrayList<DataUserAdmin>(dataUserAdminList));
  }
}
