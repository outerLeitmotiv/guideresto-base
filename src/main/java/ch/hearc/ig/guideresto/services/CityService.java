package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
import ch.hearc.ig.guideresto.business.City;
import java.util.Set;

public interface CityService {
    City findByZipCode(String zipCode);
    void addCity(City city);
    Set<City> findAll();
}
