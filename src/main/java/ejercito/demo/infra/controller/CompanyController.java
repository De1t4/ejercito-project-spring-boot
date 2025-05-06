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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Companies")
@RequestMapping("/v1/companies")
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private ServiceCompany serviceCompany;

  @GetMapping
  public ResponseEntity<Page<Company>> getCompaniesList(@RequestParam(required = false) String search, @PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.ok(serviceCompany.getCompaniesPages(pageable, search));
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
  public ResponseEntity<DataResponseCompany> modifyCompany(@RequestBody @Valid DataUpdateCompany dataUpdateCompany) {
    Company company = serviceCompany.updateCompany(dataUpdateCompany);
    return ResponseEntity.ok(dataResponseCompany(company));
  }

  @DeleteMapping("/delete")
  @Transactional
  public ResponseEntity deleteCompany(@RequestBody @Valid Set<Long> ids) {
    serviceCompany.deleteById(ids);
    return ResponseEntity.noContent().build();
  }

  private DataResponseCompany dataResponseCompany(Company company) {
    return new DataResponseCompany(company.getId_company(), company.getActivity());
  }
}
