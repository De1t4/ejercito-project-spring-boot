package ejercito.demo.service.soldier;

import ejercito.demo.models.Soldier;

import java.sql.Date;

public record DataListSoldier(Long id_soldier, String name, String lastname, Date graduation) {

  public DataListSoldier(Soldier soldier){
    this(soldier.getId_soldier(), soldier.getName(), soldier.getLastname(), soldier.getGraduation());
  }
}
