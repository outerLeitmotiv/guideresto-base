package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Localisation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalisationDataMapper extends AbstractDataMapper<Localisation> {
    private DataMapper<City> cityDataMapper = new CityDataMapper();
    @Override
    protected Localisation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        String street = resultSet.getString("STREET");
        Integer cityId = resultSet.getInt("CITY_ID");
        City city = cityDataMapper.findById(cityId); // Using findById method to get City

        return new Localisation(street, city);
    }

    @Override
    protected String getTableName() {
        return "LOCALISATIONS";
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return "ID";
    }

    @Override
    protected void setInsertParameters(Localisation obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getStreet());
        City city = obj.getCity();

        CityDataMapper cityDataMapper = new CityDataMapper(); // Create an instance of CityDataMapper
        Integer cityId = cityDataMapper.extractPrimaryKey(city);

        if (cityId == null) {
            cityDataMapper.insert(city); // Insert the City if it doesn't already exist
            cityId = cityDataMapper.extractPrimaryKey(city); // Retrieve the generated ID
        }

        statement.setInt(2, cityId);
    }

    @Override
    protected void setUpdateParameters(Localisation obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getStreet());
        City city = obj.getCity();
        CityDataMapper cityDataMapper = new CityDataMapper(); // Create an instance of CityDataMapper
        Integer cityId = cityDataMapper.extractPrimaryKey(city);
        if (cityId == null) {
            cityDataMapper.insert(city); // Insert the City if it doesn't already exist
            cityId = cityDataMapper.extractPrimaryKey(city); // Retrieve the generated ID
        }
        statement.setInt(2, cityId);
        statement.setInt(3, extractPrimaryKey(obj));
    }
    @Override
    protected String generateInsertStatement() {
        return "(STREET, CITY_ID) VALUES (?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "SET STREET = ?, CITY_ID = ? WHERE ID = ?";
    }

    @Override
    protected String getNameColumnName() {
        return null;
    }
}
