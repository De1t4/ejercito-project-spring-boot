package ejercito.demo.models;

import ejercito.demo.service.user.DataRegisterUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;


@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_user;
  private String username;
  private String password;
  private String role;
  @ManyToOne
  @JoinColumn(name = "id_soldier")
  private Soldier soldier;

  public User() {}

  public User(DataRegisterUser dataRegisterUser) {
    this.username = dataRegisterUser.username();
    this.password = new BCryptPasswordEncoder().encode(dataRegisterUser.password());
    this.role = dataRegisterUser.role();
  }

  public User(Soldier soldier , DataRegisterUser dataRegisterUser){
    this.username = dataRegisterUser.username();
    this.password = new BCryptPasswordEncoder().encode(dataRegisterUser.password());
    this.role = dataRegisterUser.role();
    this.soldier = soldier;
  }

  public Long getId_user() {
    return id_user;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.role));
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }
}
