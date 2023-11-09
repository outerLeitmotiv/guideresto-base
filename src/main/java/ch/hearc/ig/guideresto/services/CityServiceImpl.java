package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.persistence.CityDataMapper;

import java.util.List;
import java.util.Set;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class CityServiceImpl implements CityService {

    private CityDataMapper cityDataMapper;

    public CityServiceImpl() {
        this.cityDataMapper = new CityDataMapper();
    }

    @Override
    public City findById(Integer id) {
        return cityDataMapper.findById(id);
    }

    @Override
    public void addCity(City city) {
        cityDataMapper.insert(city);
    }

    @Override
    public void updateCity(City city) {
        cityDataMapper.update(city);
    }

    @Override
    public void deleteCity(City city) {
        cityDataMapper.delete(city);
    }

    @Override
    public List<City> findAllCities() {
        return cityDataMapper.findAll();
    }
}
