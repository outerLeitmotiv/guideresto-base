package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantDataMapper extends AbstractDataMapper<Restaurant> {

    private  CityDataMapper cityDataMapper;
    private  RestaurantTypeDataMapper restaurantTypeDataMapper;

    public RestaurantDataMapper() {
        cityDataMapper = new CityDataMapper();
        restaurantTypeDataMapper = new RestaurantTypeDataMapper();
    }
    @Override
    protected Restaurant mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("NUMERO");
        String name = resultSet.getString("NOM");
        String description = resultSet.getString("DESCRIPTION");
        String website = resultSet.getString("SITE_WEB");
        String address = resultSet.getString("ADRESSE");
        Integer cityId = resultSet.getInt("FK_VILL");
        Integer typeId = resultSet.getInt("FK_TYPE");
        City city = cityDataMapper.findById(cityId);
        RestaurantType type = restaurantTypeDataMapper.findById(typeId);

        return new Restaurant(id, name, description, website, address, city, type);
    }


    @Override
    protected String getTableName() {
        return "RESTAURANTS";
    }
    @Override
    protected void setInsertParameters(Restaurant obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getName());
        statement.setString(2, obj.getDescription());
        statement.setString(3, obj.getWebsite());
        statement.setString(4, obj.getAddress().getStreet());
        Integer cityId = extractPrimaryKey(obj.getAddress().getCity());
        Integer typeId = extractPrimaryKey(obj.getType());
        statement.setInt(5, typeId);
        statement.setInt(6, cityId);
    }


    @Override
    protected void setUpdateParameters(Restaurant obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getName());
        statement.setString(2, obj.getDescription());
        statement.setString(3, obj.getWebsite());
        statement.setString(4, obj.getAddress().getStreet());
        Integer typeId = extractPrimaryKey(obj.getType());
        Integer cityId = extractPrimaryKey(obj.getAddress().getCity());
        statement.setInt(5, typeId);
        statement.setInt(6, cityId);
        statement.setInt(7, obj.getId());
    }



    @Override
    protected String getPrimaryKeyColumnName() {
        return "NUMERO"; //
    }

    @Override
    protected String generateInsertStatement() {
        return "(NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_TYPE, FK_VILL) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "NOM = ?, DESCRIPTION = ?, SITE_WEB = ?, ADRESSE = ?, FK_TYPE = ?, FK_VILL = ?";
    }


    @Override
    protected String getNameColumnName() {
        return "NOM";
    }

}
