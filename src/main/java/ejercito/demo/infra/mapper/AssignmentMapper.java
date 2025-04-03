package ejercito.demo.infra.mapper;

import ejercito.demo.models.Assignment;
import ejercito.demo.models.Services;
import ejercito.demo.service.assignment.dto.response.DataResponseAssignment;
import ejercito.demo.service.soldier.DataResponseSoldierBasic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AssignmentMapper {

  public DataResponseAssignment toDataResponseAssignment(Services services) {
    return new DataResponseAssignment(
            services.getId_service(),
            services.getDescription()
    );
  }

  public List<DataResponseSoldierBasic> toListSoldiers(Set<Assignment> serviceSoldiers) {
    List<DataResponseSoldierBasic> dataListSoldiers = new ArrayList<>();
    if (serviceSoldiers.isEmpty()) {
      return dataListSoldiers;
    }

    for (Assignment soldier : serviceSoldiers) {
      DataResponseSoldierBasic dataResponseAssignment =
              new DataResponseSoldierBasic(soldier.getSoldier().getId_soldier(), soldier.getSoldier().getName(), soldier.getSoldier().getLastname(), soldier.getSoldier().getGraduation());
      dataListSoldiers.add(dataResponseAssignment);
    }
    return dataListSoldiers;
  }

}
