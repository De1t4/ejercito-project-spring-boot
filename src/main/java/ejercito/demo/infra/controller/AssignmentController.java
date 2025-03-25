package ejercito.demo.infra.controller;

import ejercito.demo.infra.mapper.AssignmentMapper;
import ejercito.demo.infra.mapper.SoldierMapper;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.Services;
import ejercito.demo.service.Assignment.AssignmentService;
import ejercito.demo.service.Assignment.DataRegisterSoldierAssignment;
import ejercito.demo.service.Assignment.DataResponseAssignment;
import ejercito.demo.service.soldier.DataResponseSoldierBasic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class AssignmentController {

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private AssignmentMapper assignmentMapper;

  @GetMapping("/services/{id}/assignments")
  public ResponseEntity<DataResponseAssignment> getServiceAssignedSoldiers(@PathVariable @Valid Long id) {
    Services service = assignmentService.getServiceAssignedSoldiers(id);
    return ResponseEntity.ok(assignmentMapper.toDataResponseAssignment(service));
  }

  @PostMapping("/services/{id}/assignments")
  public ResponseEntity<List<DataResponseSoldierBasic>> createServiceAssignedSoldiers(@PathVariable @Valid Long id, @RequestBody @Valid DataRegisterSoldierAssignment dataRegisterSoldierAssignment) {
    List<Assignment> assignments = assignmentService.createServiceAssignedSoldiers(id, dataRegisterSoldierAssignment);
    return ResponseEntity.ok(assignmentMapper.toListSoldiers(new HashSet<>(assignments)));
  }



}
