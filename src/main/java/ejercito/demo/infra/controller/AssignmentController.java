package ejercito.demo.infra.controller;

import ejercito.demo.infra.mapper.AssignmentMapper;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.Services;
import ejercito.demo.service.assignment.*;
import ejercito.demo.service.assignment.dto.request.DataBothServiceAndAssignment;
import ejercito.demo.service.assignment.dto.request.DataRegisterSoldierAssignment;
import ejercito.demo.service.assignment.dto.request.DataRequestFinishAssignment;
import ejercito.demo.service.assignment.dto.response.DataAllServicesAssignment;
import ejercito.demo.service.assignment.dto.response.DataFinishResponseAssignment;
import ejercito.demo.service.assignment.dto.response.DataResponseAssignment;
import ejercito.demo.service.assignment.dto.response.DataResponseBothServiceAndAssignment;
import ejercito.demo.service.soldier.DataResponseSoldierBasic;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/admin")
public class AssignmentController {

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private AssignmentMapper assignmentMapper;

  @GetMapping("/services/assignments")
  public ResponseEntity<List<DataAllServicesAssignment>> getAllServicesAssigned(){
    List<DataAllServicesAssignment> dataAllServicesAssignments = assignmentService.getAllServicesAssigned();
    return ResponseEntity.ok(dataAllServicesAssignments);
  }

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

  @PostMapping("/services/created/assignments")
  public ResponseEntity<List<DataResponseBothServiceAndAssignment>> createBothServiceAndAssigned(@RequestBody @Valid DataBothServiceAndAssignment data) {
    List<Assignment> assignments = assignmentService.createBothServiceAndAssigned(data);
    List<DataResponseBothServiceAndAssignment> dataResponseAssignmentList = new ArrayList<>();
    for (Assignment assignment : assignments) {
      dataResponseAssignmentList.add(new DataResponseBothServiceAndAssignment(assignment.getId_services_soldiers(), assignment.getSoldier().getId_soldier(), assignment.getServices().getDescription()));
    }
    return ResponseEntity.ok(dataResponseAssignmentList);
  }

  @PutMapping("/services/finish/assignments")
  public ResponseEntity<List<DataFinishResponseAssignment>> finishServiceAssigned(@RequestBody DataRequestFinishAssignment dataRequestFinishAssignment) {
    return ResponseEntity.ok(assignmentService.finishServiceAssigned(dataRequestFinishAssignment.id_services_soldiers()));
  }


}
