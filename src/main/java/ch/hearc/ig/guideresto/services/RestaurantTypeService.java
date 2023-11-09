package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
import ch.hearc.ig.guideresto.business.RestaurantType;

import java.util.List;
import java.util.Set;

public interface RestaurantTypeService {
    RestaurantType findById(Integer id);
    void addRestaurantType(RestaurantType restaurantType);
    void updateRestaurantType(RestaurantType restaurantType);
    void deleteRestaurantType(RestaurantType restaurantType);
    List<RestaurantType> findAllRestaurantTypes();
}

