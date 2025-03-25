package ejercito.demo.service.Assignment;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.repository.AssignmentRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.Services;
import ejercito.demo.models.Soldier;
import ejercito.demo.service.service.ServiceSoldierServices;
import ejercito.demo.service.soldier.ServiceSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

  @Autowired
  private ServiceSoldierServices serviceSoldierServices;

  @Autowired
  private ServiceSoldier serviceSoldier;

  @Autowired
  private AssignmentRepository assignmentRepository;

  public Services getServiceAssignedSoldiers(Long id) {
    return serviceSoldierServices.getServiceById(id);
  }

  public List<Assignment> createServiceAssignedSoldiers(Long id_service, DataRegisterSoldierAssignment idSoldiers) {
    if (idSoldiers == null || idSoldiers.id_soldiers().isEmpty()) {
      throw new BadRequestException("ARRAY LIST WITH SOLDIERS IS EMPTY OR NULL");
    }

    Services service = serviceSoldierServices.getServiceById(id_service);

    List<Soldier> soldiers = serviceSoldier.getSoldiersByIds(idSoldiers.id_soldiers());

    List<Assignment> assignments = soldiers.stream()
            .map(soldier -> new Assignment(soldier, service))
            .collect(Collectors.toList());

    return assignmentRepository.saveAll(assignments);
  }

  public List<Assignment> getLisServicesByIdSoldier(Long id_soldier){
   return assignmentRepository.findBySoldierId(id_soldier);
  }

}
