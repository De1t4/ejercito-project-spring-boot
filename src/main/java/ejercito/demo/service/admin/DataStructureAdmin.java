package ejercito.demo.service.admin;

import ejercito.demo.models.Barrack;
import ejercito.demo.models.Body;
import ejercito.demo.models.Company;

import java.util.List;

public record DataStructureAdmin(
        List<Company> companies, List<Body> army_bodies, List<Barrack> barracks
        ) {
}
