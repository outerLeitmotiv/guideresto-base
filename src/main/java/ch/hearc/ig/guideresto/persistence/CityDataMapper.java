package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDataMapper extends AbstractDataMapper<City> {

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
    protected String getPrimaryKeyColumnName() {
        return "NUMERO";
    }
    protected void setInsertParameters(City obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getZipCode());
        statement.setString(2, obj.getCityName());
    }
    protected void setUpdateParameters(City obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getZipCode());
        statement.setString(2, obj.getCityName());
        statement.setInt(3, extractPrimaryKey(obj));
    }
    @Override
    protected String generateInsertStatement() {
        return "(CODE_POSTAL, NOM_VILLE) VALUES (?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "CODE_POSTAL = ?, NOM_VILLE = ?";
    }
    @Override
    protected String getNameColumnName() {
        return "NOM_VILLE";
    }
}

