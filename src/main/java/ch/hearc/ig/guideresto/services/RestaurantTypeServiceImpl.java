package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.RestaurantTypeDataMapper;

import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class RestaurantTypeServiceImpl implements RestaurantTypeService {

    private RestaurantTypeDataMapper restaurantTypeDataMapper;

    public RestaurantTypeServiceImpl() {
        this.restaurantTypeDataMapper = new RestaurantTypeDataMapper();
    }

    @Override
    public RestaurantType findById(Integer id) {
        return restaurantTypeDataMapper.findById(id);
    }

    @Override
    public void addRestaurantType(RestaurantType restaurantType) {
        restaurantTypeDataMapper.insert(restaurantType);
    }

    @Override
    public void updateRestaurantType(RestaurantType restaurantType) {
        restaurantTypeDataMapper.update(restaurantType);
    }

    @Override
    public void deleteRestaurantType(RestaurantType restaurantType) {
        restaurantTypeDataMapper.delete(restaurantType);
    }

    @Override
    public List<RestaurantType> findAllRestaurantTypes() {
        return restaurantTypeDataMapper.findAll();
    }
}
