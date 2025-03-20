package ejercito.demo.models;

import ejercito.demo.service.barrack.DataRegisterBarrack;
import ejercito.demo.service.barrack.DataUpdateBarrack;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "barracks")
@Entity(name = "Barrack")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barrack {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_barrack;
  private String name;
  private String location;

  public Barrack(Long id_barrack, String name, String location) {
    this.id_barrack = id_barrack;
    this.name = name;
    this.location = location;
  }

  public Barrack(DataRegisterBarrack dataRegisterBarrack){
    this.name = dataRegisterBarrack.name();
    this.location = dataRegisterBarrack.location();
  }

  public Barrack() {
  }

  public Long getId_barrack() {
    return id_barrack;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public void updateDataSoldier(DataUpdateBarrack dataUpdateBarrack) {
    if(dataUpdateBarrack.name() != null){
      this.name = dataUpdateBarrack.name();
    }
    if(dataUpdateBarrack.location() != null){
      this.location = dataUpdateBarrack.location();
    }
  }

  public void setId_barrack(Long id_barrack) {
    this.id_barrack = id_barrack;
  }
}
