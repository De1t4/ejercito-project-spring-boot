package ejercito.demo.service.company;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.CompanyRepository;
import ejercito.demo.models.Company;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Optional;

@Service
public class ServiceCompany {

  @Autowired
  private CompanyRepository companyRepository;

  public Company getCompanyById(Long id) throws NotFoundException {
    Optional<Company> company = companyRepository.findById(id);
    if(!company.isPresent()){
      throw new NotFoundException("NOT FOUND WITH ID " + id + " COMPANY");
    }
    return company.get();
  }

  public Company createCompanyData(DataRegisterCompany dataRegisterCompany) throws BadRequestException{
    if(dataRegisterCompany.activity() == null && dataRegisterCompany.activity().isEmpty()){
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

  private boolean existCompany(Long id) throws NotFoundException{
      Optional<Company> company = companyRepository.findById(id);
      if(!company.isPresent()) {
        throw new NotFoundException("NOT FOUND WITH ID " + id + " COMPANY");
      }
      return true;
  }
}
