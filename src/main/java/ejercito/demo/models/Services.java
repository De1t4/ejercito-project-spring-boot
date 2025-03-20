package ejercito.demo.models;

import ejercito.demo.service.service.DataRegisterService;
import ejercito.demo.service.service.DataUpdateServices;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
  private Date end_service;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_soldier")
  private Soldier soldier;

  public Services() {
  }

  public Services(DataRegisterService data, Soldier soldier) {
    this.description = data.description();
    this.end_service = data.end_service();
    this.soldier = soldier;
  }

  public Long getId_service() {
    return id_service;
  }

  public String getDescription() {
    return description;
  }

  public Date getEnd_service() {
    return end_service;
  }

  public Soldier getSoldier() {
    return soldier;
  }

  public void updateDataService(DataUpdateServices data, Soldier soldier) {
    if(data.description() != null){
      this.description = data.description();
    }
    if(data.end_service() != null){
      this.end_service = data.end_service();
    }
    if(soldier != null){
      this.soldier = soldier;
    }
  }
}
