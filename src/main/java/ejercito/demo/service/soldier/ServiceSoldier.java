package ejercito.demo.service.soldier;

import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.BarrackRepository;
import ejercito.demo.infra.repository.BodyRepository;
import ejercito.demo.infra.repository.CompanyRepository;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.models.Barrack;
import ejercito.demo.models.Body;
import ejercito.demo.models.Company;
import ejercito.demo.models.Soldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Optional;

@Service
public class ServiceSoldier {
  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private BarrackRepository barrackRepository;

  @Autowired
  private BodyRepository bodyRepository;

  public Soldier getUserByName(String name) {
    return soldierRepository.findByName(name);
  }

  public Soldier createSoldierWithData(DataRegisterSoldier dataRegisterSoldier){
    Company company = companyRepository.findById(dataRegisterSoldier.id_company()).orElseThrow(() -> new RuntimeException("Company not found"));;
    Barrack barrack = barrackRepository.findById(dataRegisterSoldier.id_barrack()).orElseThrow(() -> new RuntimeException("Barrack not found"));
    Body body = bodyRepository.findById(dataRegisterSoldier.id_body()).orElseThrow(() -> new RuntimeException("Army body not found "));
    return new Soldier(dataRegisterSoldier, company, barrack, body);
  }

  public Soldier getSoldierById(Long id) throws NotFoundException {
    Optional<Soldier> soldier = soldierRepository.findById(id);
    if(!soldier.isPresent()){
      throw new NotFoundException("NOT FOUND SOLDIER WITH ID " + id);
    }
    return soldier.get();
  }
}
