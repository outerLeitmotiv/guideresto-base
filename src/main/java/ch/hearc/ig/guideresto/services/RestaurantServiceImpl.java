package ch.hearc.ig.guideresto.services;


import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.DataMapperException;
import ch.hearc.ig.guideresto.persistence.RestaurantDataMapper;

import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantDataMapper restaurantDataMapper;

  @Override
    public List<Restaurant> findAll() {
        try {
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

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try {
            restaurantDataMapper.insert(restaurant);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error adding a new restaurant", e);
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try {
            restaurantDataMapper.update(restaurant);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error updating restaurant", e);
        }
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        try {
            restaurantDataMapper.delete(restaurant);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error deleting restaurant : " + restaurant, e);
        }
    }
}
