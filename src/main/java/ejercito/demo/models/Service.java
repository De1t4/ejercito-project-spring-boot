package ejercito.demo.models;

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
public class Service {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_service;
  private String description;
  private Date end_service;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_soldier")
  private Soldier soldier;

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
}
