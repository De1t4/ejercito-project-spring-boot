package ejercito.demo.service.body;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.BodyRepository;
import ejercito.demo.models.Barrack;
import ejercito.demo.models.Body;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServiceBody {

  @Autowired
  private BodyRepository bodyRepository;

  public Body getBodyById(Long id) {
    return findBodyArmy(id);
  }

  @Transactional
  public Body modifyBodyArmy(DataUpdateBody dataUpdateBody) {
    Body body = findBodyArmy(dataUpdateBody.id_body());
    body.updateBodyArmy(dataUpdateBody);
    return body;
  }

  @Transactional
  public void deleteBodyArmy(Set<Long> idBodies) {
    if(idBodies.isEmpty()){
      throw new BadRequestException("List Army Bodies is empty");
    }
    for(Long id: idBodies){
     findBodyArmy(id);
    }
    bodyRepository.deleteAllById(idBodies);
  }

  public Body createBodyArmy(DataRegisterBody dataRegisterBody) {
    validateFields(dataRegisterBody.denomination(), "denomination");
    return bodyRepository.save(new Body(dataRegisterBody));
  }

  private void validateFields(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }

  private Body findBodyArmy(Long id_body) {
    return bodyRepository.findById(id_body)
            .orElseThrow(() -> new NotFoundException("Body Army with ID " + id_body + " not found"));
  }


}
