package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
import ch.hearc.ig.guideresto.business.RestaurantType;
import java.util.Set;

public interface RestaurantTypeService {
    RestaurantType findByLabel(String label);
    void addRestaurantType(RestaurantType restaurantType);
    Set<RestaurantType> findAll();
}
