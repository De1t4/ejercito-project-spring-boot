package ejercito.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "role")
@Entity(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {
  @Id
  private String role;
  private String description;

  public String getRole() {
    return role;
  }

  public String getDescription() {
    return description;
  }
}
