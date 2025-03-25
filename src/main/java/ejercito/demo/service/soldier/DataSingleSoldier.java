package ejercito.demo.service.soldier;

import ejercito.demo.models.Assignment;
import ejercito.demo.models.Soldier;
import ejercito.demo.service.Assignment.DataRequestAssignment;

import java.util.List;

public record DataSingleSoldier(Soldier soldier, List<DataRequestAssignment> assignmentList) {
}
