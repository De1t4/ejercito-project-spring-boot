package ejercito.demo.models;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Date;
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
  private Date end_service;

  public Assignment(Date end_service, LocalDateTime at_service, Services services, Soldier soldier) {
    this.end_service = end_service;
    this.at_service = at_service;
    this.services = services;
    this.soldier = soldier;
  }

  public Assignment(Soldier soldier, Services services) {
    this.soldier = soldier;
    this.services = services;
  }

  public Assignment() {
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

  public Date getEnd_service() {
    return end_service;
  }
}
