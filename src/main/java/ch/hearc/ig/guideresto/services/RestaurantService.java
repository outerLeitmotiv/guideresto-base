package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */

import ch.hearc.ig.guideresto.business.Restaurant;
import java.util.Set;

public interface RestaurantService {
    Set<Restaurant> findAll();
    Restaurant findByName(String name);
    Set<Restaurant> findByCity(String cityName);
    Set<Restaurant> findByType(String typeName);
    void addRestaurant(Restaurant restaurant);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(Restaurant restaurant);
}
