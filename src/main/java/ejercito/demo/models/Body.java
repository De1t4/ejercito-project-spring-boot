package ejercito.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  private Long id_body;
  private String denomination;

  public Body(Long id_body, String denomination) {
    this.id_body = id_body;
    this.denomination = denomination;
  }

  public Body() {
  }

  public String getDenomination() {
    return denomination;
  }

  public Long getId_body() {
    return id_body;
  }
}
