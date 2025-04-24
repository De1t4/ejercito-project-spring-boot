package ejercito.demo.service.assignment;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.AssignmentRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.Services;
import ejercito.demo.models.Soldier;
import ejercito.demo.service.assignment.dto.request.DataBothServiceAndAssignment;
import ejercito.demo.service.assignment.dto.request.DataRegisterSoldierAssignment;
import ejercito.demo.service.assignment.dto.request.DataUpdateAssignment;
import ejercito.demo.service.assignment.dto.response.DataAllServicesAssignment;
import ejercito.demo.service.assignment.dto.response.DataFinishResponseAssignment;
import ejercito.demo.service.service.DataRegisterService;
import ejercito.demo.service.service.ServiceSoldierServices;
import ejercito.demo.service.soldier.ServiceSoldier;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  public List<Assignment> getListServicesByIdSoldier(Long id_soldier) {
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
    for (Assignment assignment : assignments) {
      assignment.finishServiceAssigned();
    }
    List<DataFinishResponseAssignment> dataFinishResponseAssignments = new ArrayList<>();
    for (Assignment assignment : assignments) {
      dataFinishResponseAssignments.add(new DataFinishResponseAssignment(assignment.getId_services_soldiers(), assignment.getSoldier().getId_soldier(), assignment.getEnd_service(), assignment.getAt_service(), assignment.getServices().getDescription()));
    }

    return dataFinishResponseAssignments;
  }

  public void validateListSoldiers(List<Long> ids) {
    if (ids == null || ids.isEmpty()) {
      throw new BadRequestException("ARRAY LIST IS EMPTY OR NULL");
    }

    for (Long id : ids) {
      if (id == null) {
        throw new BadRequestException("THE LIST ARRAY HAS NULL VALUE");
      }
    }
  }

  public List<Assignment> getAssignmentByIds(List<Long> ids) {
    return assignmentRepository.getAssignmentsByIds(ids);
  }

  private Assignment findServiceAssigned(Long id) throws NotFoundException {

    return assignmentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Assignment with ID " + id + " not found"));
  }

  public Page<DataAllServicesAssignment> getAllServicesAssigned(Pageable pageable) {
    Page<Assignment> assignmentPage = assignmentRepository.findAll(pageable);
    return assignmentPage.map(service ->
            new DataAllServicesAssignment(
                    service.getId_services_soldiers(),
                    service.getServices().getDescription(),
                    service.getSoldier().getId_soldier(),
                    service.getSoldier().getName() + " " + service.getSoldier().getLastname(),
                    service.getAt_service(),
                    service.getEnd_service(),
                    service.getServices().getId_service()
            )
    );
  }

  @Transactional
  public DataFinishResponseAssignment updateServiceAssigned(Long id, DataUpdateAssignment dataUpdateAssignment) {
    if(id == null){
      throw  new BadRequestException("Assignment id is null");
    }
    Assignment assignment = findServiceAssigned(id);
    if (dataUpdateAssignment.id_service() != null) {
      Services service = serviceSoldierServices.getServiceById(dataUpdateAssignment.id_service());
      assignment.updateService(service);
      assignmentRepository.save(assignment);
      return createDataFinishResponse(assignment);
    } else if (dataUpdateAssignment.description() != null) {
      DataRegisterService dataRegisterService = new DataRegisterService(dataUpdateAssignment.description());
      Services newService = serviceSoldierServices.createService(dataRegisterService);
      assignment.updateService(newService);
      assignmentRepository.save(assignment);
      return createDataFinishResponse(assignment);
    } else {
      return createDataFinishResponse(assignment);
    }
  }
  private DataFinishResponseAssignment createDataFinishResponse(Assignment assignment) {
    return new DataFinishResponseAssignment(
            assignment.getId_services_soldiers(),
            assignment.getId_services_soldiers(), // ¿Seguro que estos dos IDs son siempre iguales?
            assignment.getEnd_service(),
            assignment.getAt_service(),
            assignment.getServices().getDescription()
    );
  }
}
