package ejercito.demo.models;

import ejercito.demo.service.body.DataRegisterBody;
import ejercito.demo.service.body.DataUpdateBody;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "army_bodies")
@Entity(name = "Body")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Body {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_body;
  private String denomination;

  public Body(Long id_body, String denomination) {
    this.id_body = id_body;
    this.denomination = denomination;
  }

  public Body() {
  }

  public Body(DataRegisterBody dataRegisterBody) {
    this.denomination = dataRegisterBody.denomination();
  }

  public String getDenomination() {
    return denomination;
  }

  public Long getId_body() {
    return id_body;
  }

  public void updateBodyArmy(DataUpdateBody dataUpdateBody) {
    this.denomination = dataUpdateBody.denomination();
  }
}
