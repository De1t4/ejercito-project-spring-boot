package ejercito.demo.infra.controller;

import ejercito.demo.models.User;
import ejercito.demo.service.subOfficial.DataCreateSubOfficial;
import ejercito.demo.service.subOfficial.DataSubOfficial;
import ejercito.demo.service.subOfficial.DataUpdateSubOfficial;
import ejercito.demo.service.subOfficial.ServiceSubOfficial;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/v1/sub-official")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Sub Official")
public class SubOfficialController {

  @Autowired
  public ServiceSubOfficial serviceSubOfficial;

  @GetMapping
  private ResponseEntity<Page<DataSubOfficial>> getPageDataSubOficials(@PageableDefault(size = 10) Pageable pageable){
    Page<DataSubOfficial> dataSubOficialAdmins = serviceSubOfficial.getAllSubOficialsData(pageable);
    return ResponseEntity.ok(dataSubOficialAdmins);
  }

  @GetMapping("/{id}")
  private ResponseEntity<DataSubOfficial> getDataSubOfficial(@PathVariable Long id){
    return ResponseEntity.ok(serviceSubOfficial.getDataSubOfficial(id));
  }

  @PostMapping
  private ResponseEntity<DataSubOfficial> createSubOfficial(@RequestBody DataCreateSubOfficial dataCreateSubOfficial, UriComponentsBuilder uriComponentsBuilder){
    User user = serviceSubOfficial.createSubOfficial(dataCreateSubOfficial);
    URI uri = uriComponentsBuilder.path("/sub-official/{id}").buildAndExpand(user.getId_user()).toUri();
    return ResponseEntity.created(uri).body(new DataSubOfficial(user.getId_user(), user.getPassword(), user.getSoldier() == null ? null: user.getSoldier() ));
  }

  @PutMapping
  private ResponseEntity<DataSubOfficial> updateSubOfficial(@RequestBody DataUpdateSubOfficial dataUpdateSubOfficial){
    return ResponseEntity.ok(serviceSubOfficial.updateSubOfficial(dataUpdateSubOfficial));
  }

  @DeleteMapping("/delete")
  private ResponseEntity<Void> deleteSubOfficials(@RequestBody Set<Long> ids){
    serviceSubOfficial.deleteSubOfficials(ids);
    return ResponseEntity.noContent().build();
  }

}
