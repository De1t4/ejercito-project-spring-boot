package ejercito.demo.service.soldier;

import ejercito.demo.models.Soldier;
import ejercito.demo.service.assignment.dto.request.DataRequestAssignment;

import java.util.List;

public record DataSingleSoldier(Soldier soldier, List<DataRequestAssignment> assignmentList) {
}
