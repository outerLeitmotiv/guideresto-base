package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantDataMapper extends AbstractDataMapper<Restaurant> {

    @Override
    protected Restaurant mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("ID");
        String name = resultSet.getString("NAME");
        String description = resultSet.getString("DESCRIPTION");
        String website = resultSet.getString("WEBSITE");
        String street = resultSet.getString("STREET");
        Integer cityId = resultSet.getInt("CITY_ID");
        Integer typeId = resultSet.getInt("TYPE_ID");

        // Use find methods which use cache instead of creating a new DataMapper instance
        City city = (cityId != null) ? new CityDataMapper().findById(cityId) : null;
        RestaurantType type = (typeId != null) ? new RestaurantTypeDataMapper().findById(typeId) : null;

        return new Restaurant(id, name, description, website, street, city, type);
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
        statement.setString(4, obj.getStreet());
        Integer cityId = extractPrimaryKey(obj.getAddress().getCity());
        Integer typeId = extractPrimaryKey(obj.getType());
        statement.setInt(5, cityId);
        statement.setInt(6, typeId);
    }

    @Override
    protected void setUpdateParameters(Restaurant obj, PreparedStatement statement) throws SQLException {
        // Similar to setInsertParameters, set all the properties first
        statement.setString(1, obj.getName());
        statement.setString(2, obj.getDescription());
        statement.setString(3, obj.getWebsite());
        statement.setString(4, obj.getStreet());
        Integer cityId = extractPrimaryKey(obj.getAddress().getCity());
        Integer typeId = extractPrimaryKey(obj.getType());
        statement.setInt(5, cityId);
        statement.setInt(6, typeId);
        statement.setInt(7, extractPrimaryKey(obj));
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return "NUMERO"; //
    }

    @Override
    protected String generateInsertStatement() {
        return "(NAME, DESCRIPTION, WEBSITE, STREET, CITY_ID, TYPE_ID) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "NAME = ?, DESCRIPTION = ?, WEBSITE = ?, STREET = ?, CITY_ID = ?, TYPE_ID = ?";
    }

    @Override
    protected String getNameColumnName() {
        return "NOM";
    }
}
