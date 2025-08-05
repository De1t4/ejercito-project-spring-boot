package ejercito.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "services_soldiers")
@Entity(name = "Assignment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Assignment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_services_soldiers;

  @ManyToOne
  @JoinColumn(name = "id_soldier", nullable = false)
  private Soldier soldier;

  @ManyToOne
  @JoinColumn(name = "id_service", nullable = false)
  private Services services;

  @CreationTimestamp
  private LocalDateTime at_service;
  private LocalDateTime end_service;

  public Assignment(LocalDateTime end_service, LocalDateTime at_service, Services services, Soldier soldier) {
    this.end_service = end_service;
    this.at_service = at_service;
    this.services = services;
    this.soldier = soldier;
  }

  public Assignment(Soldier soldier, Services services) {
    this.soldier = soldier;
    this.services = services;
  }

  public void finishServiceAssigned() {
    this.end_service = LocalDateTime.now();
  }

  public Long getId_services_soldiers() {
    return id_services_soldiers;
  }

  public Soldier getSoldier() {
    return soldier;
  }

  public Services getServices() {
    return services;
  }

  public LocalDateTime getAt_service() {
    return at_service;
  }

  public LocalDateTime getEnd_service() {
    return end_service;
  }

  public void updateService(Services service) {
    this.services = service;
  }
}
