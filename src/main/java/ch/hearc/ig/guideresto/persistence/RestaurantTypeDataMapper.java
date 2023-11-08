package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.RestaurantType;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantTypeDataMapper extends AbstractDataMapper<RestaurantType> {

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
    protected String getPrimaryKeyColumnName() {
        return "ID";
    }

    @Override
    protected void setInsertParameters(RestaurantType obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getLabel());
        statement.setString(2, obj.getDescription());
    }

    @Override
    protected void setUpdateParameters(RestaurantType obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getLabel());
        statement.setString(2, obj.getDescription());
        statement.setInt(3, extractPrimaryKey(obj));
    }
    @Override
    protected String generateInsertStatement() {
        return "(LABEL, DESCRIPTION) VALUES (?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "SET LABEL = ?, DESCRIPTION = ? WHERE ID = ?";
    }
}
