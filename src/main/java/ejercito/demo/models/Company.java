package ejercito.demo.models;

import ejercito.demo.service.company.DataRegisterCompany;
import ejercito.demo.service.company.DataUpdateCompany;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "companies")
@Entity(name = "Company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_company;
  private String activity;


  public Company(DataRegisterCompany dataRegisterCompany) {
    this.activity = dataRegisterCompany.activity();
  }

  public Company(Long id_company, String activity) {
    this.id_company = id_company;
    this.activity = activity;
  }

  public Company() {
  }

  public void updateDataCompany(DataUpdateCompany dataUpdateCompany) {
    if (dataUpdateCompany.activity() != null) {
      this.activity = dataUpdateCompany.activity();
    }
  }

  public Long getId_company() {
    return id_company;
  }

  public String getActivity() {
    return activity;
  }


}
