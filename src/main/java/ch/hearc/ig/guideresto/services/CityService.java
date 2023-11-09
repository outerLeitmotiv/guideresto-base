package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
import ch.hearc.ig.guideresto.business.City;

import java.util.List;

public interface CityService {
    City findById(Integer id);
    void addCity(City city);
    void updateCity(City city);
    void deleteCity(City city);
    List<City> findAllCities();
}