package ejercito.demo.service.company;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.CompanyRepository;
import ejercito.demo.models.Barrack;
import ejercito.demo.models.Company;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ServiceCompany {

  @Autowired
  private CompanyRepository companyRepository;

  public Page<Company> getCompaniesPages(Pageable pageable, String search){
    if(search != null){
      return companyRepository.getPageCompaniesFounded(pageable, search);
    }
    return companyRepository.findAll(pageable);
  }

  public Company getCompanyById(Long id) throws NotFoundException {
    return findCompany(id);
  }

  public Company createCompanyData(DataRegisterCompany dataRegisterCompany) throws BadRequestException {
    if (dataRegisterCompany.activity() == null && dataRegisterCompany.activity().isEmpty()) {
      throw new BadRequestException("NOT FOUND FIELD ACTIVITY");
    }
    return new Company(dataRegisterCompany);
  }

  public Company updateCompany(@Valid DataUpdateCompany dataUpdateCompany) {
    existCompany(dataUpdateCompany.id_company());

    Company company = companyRepository.getReferenceById(dataUpdateCompany.id_company());

    company.updateDataCompany(dataUpdateCompany);
    return company;
  }

  private boolean existCompany(Long id) throws NotFoundException {
    Optional<Company> company = companyRepository.findById(id);
    if (!company.isPresent()) {
      throw new NotFoundException("NOT FOUND WITH ID " + id + " COMPANY");
    }
    return true;
  }

  public void deleteById(@Valid Set<Long> idCompanies) {
    if (idCompanies.isEmpty()) {
      throw new BadRequestException("List barracks is empty");
    }
    for (Long id : idCompanies) {
      findCompany(id);
    }
    companyRepository.deleteAllById(idCompanies);

  }

  private Company findCompany(Long idCompany) {
    return companyRepository.findById(idCompany)
            .orElseThrow(() -> new NotFoundException("Company with ID " + idCompany + " not found"));
  }

}
