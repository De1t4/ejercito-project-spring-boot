package ejercito.demo.infra.controller;


import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.CompanyRepository;
import ejercito.demo.models.Company;
import ejercito.demo.service.company.DataRegisterCompany;
import ejercito.demo.service.company.DataResponseCompany;
import ejercito.demo.service.company.DataUpdateCompany;
import ejercito.demo.service.company.ServiceCompany;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/companies")
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private ServiceCompany serviceCompany;

  @GetMapping
  public ResponseEntity<List<Company>> getCompaniesList(){
    return ResponseEntity.ok(companyRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponseCompany> getCompanyById(@PathVariable Long id) throws NotFoundException {
    return ResponseEntity.ok(dataResponseCompany(serviceCompany.getCompanyById(id)));
  }

  @PostMapping
  public ResponseEntity<Company> createCompany(@RequestBody @Valid DataRegisterCompany dataRegisterCompany, UriComponentsBuilder uriComponentsBuilder) throws BadRequestException {
    Company company = companyRepository.save(serviceCompany.createCompanyData(dataRegisterCompany));
    URI url = uriComponentsBuilder.path("/companies/{id}").buildAndExpand(company.getId_company()).toUri();
    return ResponseEntity.created(url).body(company);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataResponseCompany> modifyCompany(@RequestBody @Valid DataUpdateCompany dataUpdateCompany){
    Company company = serviceCompany.updateCompany(dataUpdateCompany);
    return ResponseEntity.ok(dataResponseCompany(company));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deleteCompany(@PathVariable @Valid Long id){
    companyRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  private DataResponseCompany dataResponseCompany(Company company){
    return new DataResponseCompany(company.getId_company(), company.getActivity());
  }
}
