package ejercito.demo.infra.mapper;

import ejercito.demo.models.*;
import ejercito.demo.service.Assignment.DataAssignment;
import ejercito.demo.service.Assignment.DataRequestAssignment;
import ejercito.demo.service.profile.DataStatusService;
import ejercito.demo.service.profile.DataStatusServicesProfile;
import ejercito.demo.service.soldier.DataResponseSoldier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SoldierMapper {

  public DataResponseSoldier toDataSoldier(Soldier soldier) {
    return new DataResponseSoldier(
            soldier.getId_soldier(), soldier.getName(), soldier.getLastname(), soldier.getGraduation(),
            new Company(soldier.getCompany().getId_company(), soldier.getCompany().getActivity()),
            new Barrack(soldier.getBarrack().getId_barrack(), soldier.getBarrack().getName(), soldier.getBarrack().getLocation()),
            new Body(soldier.getBody().getId_body(), soldier.getBody().getDenomination()));
  }

  public List<DataRequestAssignment> toDataServicesSoldier(List<Assignment> assignmentList) {
    List<DataRequestAssignment> dataRequestAssignments = new ArrayList<>();
    if (assignmentList.isEmpty()) {
      return null;
    }
    for (Assignment assignment : assignmentList) {
      DataRequestAssignment dataRequestAssignment =
              new DataRequestAssignment(
                      assignment.getId_services_soldiers(), assignment.getServices().getDescription());
      dataRequestAssignments.add(dataRequestAssignment);
    }
    return dataRequestAssignments;
  }

  public DataStatusServicesProfile toOrderServicesForStatus(List<Assignment> assignmentList) {
    if (assignmentList == null || assignmentList.isEmpty()) {
      return new DataStatusServicesProfile(Collections.emptyList(), Collections.emptyList());
    }

    List<DataStatusService> listFinishedServices = new ArrayList<>();
    List<DataStatusService> listCompletedServices = new ArrayList<>();

    for (Assignment assignment : assignmentList) {
      DataStatusService dataStatusService = mapToDataStatusService(assignment);

      if (assignment.getEnd_service() == null) {
        listFinishedServices.add(dataStatusService);
      } else {
        listCompletedServices.add(dataStatusService);
      }
    }
    return new DataStatusServicesProfile(listCompletedServices, listFinishedServices);
  }

  private DataStatusService mapToDataStatusService(Assignment assignment) {
    return new DataStatusService(
            assignment.getId_services_soldiers(),
            assignment.getAt_service(),
            assignment.getEnd_service(),
            assignment.getServices().getDescription()
    );
  }

  public DataAssignment toDataSoldierWithServices(List<Assignment> assignments, Soldier soldier) {
    if (assignments == null) {
      return new DataAssignment(toDataSoldier(soldier), null);
    }
    List<DataRequestAssignment> dataRequestAssignments = toDataServicesSoldier(assignments);
    return new DataAssignment(toDataSoldier(soldier), dataRequestAssignments);
  }
}
