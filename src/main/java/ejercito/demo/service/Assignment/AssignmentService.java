package ejercito.demo.service.Assignment;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.AssignmentRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.Services;
import ejercito.demo.models.Soldier;
import ejercito.demo.service.service.DataRegisterService;
import ejercito.demo.service.service.ServiceSoldierServices;
import ejercito.demo.service.soldier.ServiceSoldier;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    validateListSoldiers(idSoldiers.id_soldiers());

    Services service = serviceSoldierServices.getServiceById(id_service);

    List<Soldier> soldiers = serviceSoldier.getSoldiersByIds(idSoldiers.id_soldiers());

    List<Assignment> assignments = soldiers.stream()
            .map(soldier -> new Assignment(soldier, service))
            .collect(Collectors.toList());

    return assignmentRepository.saveAll(assignments);
  }

  public List<Assignment> getLisServicesByIdSoldier(Long id_soldier) {
    return assignmentRepository.findBySoldierId(id_soldier);
  }

  public List<Assignment> createBothServiceAndAssigned(DataBothServiceAndAssignment data) {
    validateListSoldiers(data.id_soldier());
    Services services = serviceSoldierServices.createService(new DataRegisterService(data.description()));
    List<Soldier> soldiers = serviceSoldier.getSoldiersByIds(data.id_soldier());

    List<Assignment> assignments = soldiers.stream()
            .map(soldier -> new Assignment(soldier, services))
            .collect(Collectors.toList());
    return assignmentRepository.saveAll(assignments);
  }

  @Transactional
  public List<DataFinishResponseAssignment> finishServiceAssigned(List<Long> ids) {
    validateListSoldiers(ids);
    List<Assignment> assignments = getAssignmentByIds(ids);
    for(Assignment assignment :assignments){
      assignment.finishServiceAssigned();
    }
    List<DataFinishResponseAssignment> dataFinishResponseAssignments = new ArrayList<>();
    for (Assignment assignment: assignments){
      dataFinishResponseAssignments.add(new DataFinishResponseAssignment(assignment.getId_services_soldiers(), assignment.getSoldier().getId_soldier(), assignment.getEnd_service(), assignment.getAt_service(), assignment.getServices().getDescription()));
    }

    return dataFinishResponseAssignments;
  }

  public void validateListSoldiers(List<Long> ids) {
    if (ids == null || ids.isEmpty()) {
      throw new BadRequestException("ARRAY LIST IS EMPTY OR NULL");
    }
  }

  public List<Assignment> getAssignmentByIds(List<Long> ids) {
    return assignmentRepository.getAssignmentsByIds(ids);
  }

  private Assignment findServiceAssigned(Long id) throws NotFoundException {
    return assignmentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Assignment with ID " + id + " not found"));
  }

}
