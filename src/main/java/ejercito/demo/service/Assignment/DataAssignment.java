package ejercito.demo.service.Assignment;

import ejercito.demo.service.soldier.DataResponseSoldier;

import java.util.List;

public record DataAssignment(DataResponseSoldier dataResponseSoldier, List<DataRequestAssignment> services) {
}
