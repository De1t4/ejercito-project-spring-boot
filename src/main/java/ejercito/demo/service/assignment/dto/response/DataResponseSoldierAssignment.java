package ejercito.demo.service.assignment.dto.response;

import ejercito.demo.service.assignment.dto.request.DataRequestAssignment;
import ejercito.demo.service.soldier.DataResponseSoldier;

import java.util.List;

public record DataResponseSoldierAssignment(DataResponseSoldier dataResponseSoldier, List<DataRequestAssignment> services) {
}
