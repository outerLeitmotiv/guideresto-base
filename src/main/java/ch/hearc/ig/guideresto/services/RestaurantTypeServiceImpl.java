package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.DataMapperException;
import ch.hearc.ig.guideresto.persistence.RestaurantTypeDataMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class RestaurantTypeServiceImpl implements RestaurantTypeService {

    private RestaurantTypeDataMapper restaurantTypeDataMapper;
    private TransactionService transactionService;

    public RestaurantTypeServiceImpl() {
        this.restaurantTypeDataMapper = new RestaurantTypeDataMapper();
        this.transactionService = new TransactionService();
    }

    @Override
    public List<RestaurantType> findAllRestaurantTypes() {
        try {
            return restaurantTypeDataMapper.findAll();
        } catch (DataMapperException e) {
            throw new RuntimeException("Error retrieving all restaurant types", e);
        }
    }

    @Override
    public RestaurantType findRestaurantTypeById(Integer id) {
        try {
            return restaurantTypeDataMapper.findById(id);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error finding restaurant type with ID: " + id, e);
        }
    }

    @Override
    public RestaurantType findById(Integer id) {
        try {
            return restaurantTypeDataMapper.findById(id);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error finding restaurant type with ID: " + id, e);
        }
    }

    @Override
    public void addRestaurantType(RestaurantType type) {
        try {
            transactionService.startTransaction();
            restaurantTypeDataMapper.insert(type);
            transactionService.commitTransaction();
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error adding a new restaurant type", e);
        }
    }

    @Override
    public void updateRestaurantType(RestaurantType type) {
        try {
            transactionService.startTransaction();
            restaurantTypeDataMapper.update(type);
            transactionService.commitTransaction();
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error updating restaurant type", e);
        }
    }

    @Override
    public void deleteRestaurantType(RestaurantType type) {
        try {
            transactionService.startTransaction();
            restaurantTypeDataMapper.delete(type);
            transactionService.commitTransaction();
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error deleting restaurant type: " + type, e);
        }
    }
}

