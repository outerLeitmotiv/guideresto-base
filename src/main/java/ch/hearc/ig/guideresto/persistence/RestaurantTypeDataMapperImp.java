package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.RestaurantType;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantTypeDataMapperImp extends AbstractDataMapper<RestaurantType> {

    @Override
    protected RestaurantType mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("ID");
        return new RestaurantType(
                id,
                resultSet.getString("LABEL"),
                resultSet.getString("DESCRIPTION")
        );
    }

    @Override
    protected String getTableName() {
        return "RESTAURANT_TYPES";
    }

    @Override
    public void insert(RestaurantType obj) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(
                    "INSERT INTO RESTAURANT_TYPES (LABEL, DESCRIPTION) VALUES (?, ?)"
            );
            statement.setString(1, obj.getLabel());
            statement.setString(2, obj.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error inserting restaurant type");
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
    public RestaurantType update(RestaurantType obj) throws DataMapperException {
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
                            "UPDATE RESTAURANT_TYPES SET LABEL = ?, DESCRIPTION = ? WHERE ID = ?"
                    );
                    statement.setString(1, obj.getLabel());
                    statement.setString(2, obj.getDescription());
                    statement.setInt(3, id);
                    statement.executeUpdate();
                    obj = findById(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error updating restaurant type");
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
            throw new DataMapperException("Error updating restaurant type");
        }
        return obj;
    }

    @Override
    public void delete(RestaurantType obj) throws DataMapperException {
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
                            "DELETE FROM RESTAURANT_TYPES WHERE ID = ?"
                    );
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error deleting restaurant type");
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
            throw new DataMapperException("Error deleting restaurant type");
        }
    }

    @Override
    public List<RestaurantType> findAll() throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM RESTAURANT_TYPES");
            ResultSet resultSet = statement.executeQuery();
            List<RestaurantType> restaurantTypes = new ArrayList<>();
            while (resultSet.next()) {
                restaurantTypes.add(new RestaurantType(
                        resultSet.getInt("ID"),
                        resultSet.getString("LABEL"),
                        resultSet.getString("DESCRIPTION")
                ));
            }
            return restaurantTypes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataMapperException("Error finding all restaurant types");
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

