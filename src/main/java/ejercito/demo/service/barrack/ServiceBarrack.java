package ejercito.demo.service.barrack;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.BarrackRepository;
import ejercito.demo.models.Barrack;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBarrack {

  @Autowired
  private BarrackRepository barrackRepository;

  public Barrack getBarrackById(@Valid Long id_barrack) {
    return findBarrackOrThrow(id_barrack);
  }

  public Barrack createBarrackWithData(@Valid DataRegisterBarrack dataRegisterBarrack) {
    validateFields(dataRegisterBarrack.name(), "NAME");
    validateFields(dataRegisterBarrack.location(), "LOCATION");
    return barrackRepository.save( new Barrack(dataRegisterBarrack));
  }

  public Barrack updateDataBarrack(DataUpdateBarrack dataUpdateBarrack) throws BadRequestException {
    if (dataUpdateBarrack.id_barrack() == null ){
      throw new BadRequestException("NOT FOUND FIELD ID_BARRACK");
    }

    Barrack barrack = findBarrackOrThrow(dataUpdateBarrack.id_barrack());
    barrack.updateDataSoldier(dataUpdateBarrack);
    return barrack;
  }

  public void deleteBarrackById(@Valid Long idBarrack) throws BadRequestException{
    if (idBarrack == null){
      throw new BadRequestException("NOT FOUND FIELD ID_BARRACK");
    }
    findBarrackOrThrow(idBarrack);
    barrackRepository.deleteById(idBarrack);
  }

  private Barrack findBarrackOrThrow(Long idBarrack) {
    return barrackRepository.findById(idBarrack)
            .orElseThrow(() -> new NotFoundException("Barrack with ID " + idBarrack + " not found"));
  }

  private void validateFields(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }
}
