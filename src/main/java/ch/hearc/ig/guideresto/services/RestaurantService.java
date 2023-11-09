package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> findAll();
    Restaurant findById(Integer id);
    void addRestaurant(Restaurant restaurant);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(Restaurant restaurant);
    public Integer findByType(RestaurantType type);


}