package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.persistence.CityDataMapper;
import ch.hearc.ig.guideresto.persistence.DataMapperException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class CityServiceImpl implements CityService {

    private CityDataMapper cityDataMapper;
    private TransactionService transactionService;

    public CityServiceImpl() {
        this.cityDataMapper = new CityDataMapper();
        this.transactionService = new TransactionService();
    }

    @Override
    public City findById(Integer id) {
        return cityDataMapper.findById(id);
    }

    public void addCity(City city) {
        try {
            transactionService.startTransaction();
            cityDataMapper.insert(city);
            transactionService.commitTransaction();
            System.out.println("City added successfully.");
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error adding a new city", e);
        }
    }

    @Override
    public void updateCity(City city) {
        try {
            transactionService.startTransaction();
            cityDataMapper.update(city);
            transactionService.commitTransaction();
            System.out.println("City updated successfully.");
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error updating city", e);
        }
    }

    @Override
    public void deleteCity(City city) {
        try {
            transactionService.startTransaction();
            cityDataMapper.delete(city);
            transactionService.commitTransaction();
            System.out.println("City deleted successfully.");
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error deleting city: " + city, e);
        }
    }

    @Override
    public List<City> findAllCities() {
        try {
            System.out.println("Retrieving all cities...");
            return cityDataMapper.findAll();
        } catch (DataMapperException e) {
            throw new RuntimeException("Error retrieving all cities", e);
        }
    }
}


