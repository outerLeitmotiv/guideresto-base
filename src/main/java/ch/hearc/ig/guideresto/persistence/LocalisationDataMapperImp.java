package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Localisation;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalisationDataMapperImp extends AbstractDataMapper<Localisation> {

    @Override
    protected Localisation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        String street = resultSet.getString("STREET");
        Integer cityId = resultSet.getInt("CITY_ID");
        City city = findById(cityId).getCity();

        return new Localisation(street, city);
    }
    @Override
    protected String getTableName() {
        return "LOCALISATIONS";
    }
    @Override
    public void insert(Localisation obj) throws DataMapperException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO LOCALISATIONS (STREET, CITY_ID) VALUES (?, ?)"
            );
            statement.setString(1, obj.getStreet());

            City city = obj.getCity();
            Integer cityId = null;
            try {
                Field idField = City.class.getDeclaredField("id");
                idField.setAccessible(true);
                cityId = (Integer) idField.get(city);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            statement.setInt(2, cityId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error inserting localisation");
        }
    }
}