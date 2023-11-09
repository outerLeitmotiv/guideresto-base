package ch.hearc.ig.guideresto.services;


import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.DataMapperException;
import ch.hearc.ig.guideresto.persistence.RestaurantDataMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantDataMapper restaurantDataMapper;
    private TransactionService transactionService;

    public RestaurantServiceImpl() {
        this.restaurantDataMapper = new RestaurantDataMapper();
        this.transactionService = new TransactionService();
    }

  @Override
    public List<Restaurant> findAll() {
        try {
            System.out.println("Finding all restaurants...");
            return restaurantDataMapper.findAll();
        } catch (DataMapperException e) {
            throw new RuntimeException("Error retrieving all restaurants", e);
        }
    }

    @Override
    public Restaurant findById(Integer id) {
        try {
            return restaurantDataMapper.findById(id);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error finding restaurant with ID: " + id, e);
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        try {
            transactionService.startTransaction();
            restaurantDataMapper.insert(restaurant);
            transactionService.commitTransaction();
            System.out.println("Restaurant added successfully.");
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error adding a new restaurant", e);
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try {
            transactionService.startTransaction();
            restaurantDataMapper.update(restaurant);
            transactionService.commitTransaction();
            System.out.println("Restaurant updated successfully.");
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error updating restaurant", e);
        }
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        try {
            transactionService.startTransaction();
            restaurantDataMapper.delete(restaurant);
            transactionService.commitTransaction();
            System.out.println("Restaurant deleted successfully.");
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error deleting restaurant : " + restaurant, e);
        }
    }
    public Integer findByType(RestaurantType type) {
        try {
            return restaurantDataMapper.extractPrimaryKey(type);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error finding restaurant with type ID: " + type, e);
        }
    }
}
