package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDataMapperImp extends AbstractDataMapper<City> {

    @Override
    protected City mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("NUMERO");
        return new City(
                id,
                resultSet.getString("CODE_POSTAL"),
                resultSet.getString("NOM_VILLE")
        );
    }

    @Override
    protected String getTableName() {
        return "VILLES";
    }

    @Override
    public void insert(City obj) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(
                    "INSERT INTO VILLES (CODE_POSTAL, NOM_VILLE) VALUES (?, ?)"
            );
            statement.setString(1, obj.getZipCode());
            statement.setString(2, obj.getCityName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error inserting city");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public City update(City obj) throws DataMapperException {
        try {
            Integer id = null;
            try {
                Field idField = obj.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                id = (Integer) idField.get(obj);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (id != null) {
                PreparedStatement statement = null;
                try {
                    statement = this.connection.prepareStatement(
                            "UPDATE VILLES SET CODE_POSTAL = ?, NOM_VILLE = ? WHERE NUMERO = ?"
                    );
                    statement.setString(1, obj.getZipCode());
                    statement.setString(2, obj.getCityName());
                    statement.setInt(3, id);
                    statement.executeUpdate();
                    obj = findById(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error updating city");
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataMapperException("Error updating city");
        }
        return obj;
    }

    @Override
    public void delete(City obj) throws DataMapperException {
        try {
            Integer id = null;
            try {
                Field idField = obj.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                id = (Integer) idField.get(obj);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (id != null) {
                PreparedStatement statement = null;
                try {
                    statement = this.connection.prepareStatement(
                            "DELETE FROM VILLES WHERE NUMERO = ?"
                    );
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error deleting city");
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataMapperException("Error deleting city");
        }
    }

    @Override
    public List<City> findAll() throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM VILLES");
            ResultSet resultSet = statement.executeQuery();
            List<City> cities = new ArrayList<>();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("NUMERO"),
                        resultSet.getString("CODE_POSTAL"),
                        resultSet.getString("NOM_VILLE")));
            }
            return cities;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataMapperException("Error finding all cities");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

