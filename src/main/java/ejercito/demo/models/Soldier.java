package ejercito.demo.models;

import ejercito.demo.service.soldier.DataRegisterSoldier;
import ejercito.demo.service.soldier.DataUpdateSoldier;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "soldiers")
@Entity(name = "Soldier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Soldier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_soldier;
  private String name;
  private String lastname;
  private Date graduation;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_company")
  private Company company;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="id_barrack")
  private Barrack barrack;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="id_body")
  private Body body;

  public Soldier(DataRegisterSoldier dataRegisterSoldier, Company company, Barrack barrack, Body body){
    this.name = dataRegisterSoldier.name();
    this.lastname = dataRegisterSoldier.lastname();
    this.graduation = dataRegisterSoldier.graduation();
    this.company = company;
    this.barrack = barrack;
    this.body = body;
  }

  public void updateDataSoldier(DataUpdateSoldier dataUpdateSoldier) {
    if (dataUpdateSoldier.name() != null) {
      this.name = dataUpdateSoldier.name();
    }
    if (dataUpdateSoldier.lastname() != null) {
      this.lastname = dataUpdateSoldier.lastname();
    }
    if (dataUpdateSoldier.graduation() != null) {
      this.graduation = dataUpdateSoldier.graduation();
    }
  }

  public Soldier() {
  }

  public Company getCompany() {
    return company;
  }

  public Barrack getBarrack() {
    return barrack;
  }

  public Body getBody() {
    return body;
  }

  public Long getId_soldier() {
    return id_soldier;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public Date getGraduation() {
    return graduation;
  }
}
