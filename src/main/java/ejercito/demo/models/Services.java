package ejercito.demo.models;

import ejercito.demo.service.service.DataRegisterService;
import ejercito.demo.service.service.DataUpdateServices;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "services")
@Entity(name = "Service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Services {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_service;
  private String description;
  @OneToMany(mappedBy = "services", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<Assignment> serviceSoldiers = new HashSet<>();

  public Services(DataRegisterService dataRegisterService) {
    this.description = dataRegisterService.description();
  }

  public Services() {
  }

  public Long getId_service() {
    return id_service;
  }

  public String getDescription() {
    return description;
  }

  public Set<Assignment> getServiceSoldiers() {
    return serviceSoldiers;
  }

  public void updateDataService(DataUpdateServices dataUpdateServices) {
    this.description = dataUpdateServices.description();
  }
}
