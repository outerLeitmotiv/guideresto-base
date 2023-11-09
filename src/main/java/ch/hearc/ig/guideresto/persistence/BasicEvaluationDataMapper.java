package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BasicEvaluationDataMapper extends AbstractDataMapper<BasicEvaluation> {

    @Override
    protected BasicEvaluation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("NUMERO");
        LocalDate visitDate = resultSet.getDate("DATE_EVAL").toLocalDate();
        boolean likeRestaurant = resultSet.getString("APPRECIATION").charAt(0) == 'T';
        String ipAddress = resultSet.getString("ADRESSE_IP");
        Restaurant restaurant = new RestaurantDataMapper().findById(resultSet.getInt("FK_REST"));
        return new BasicEvaluation(id, visitDate, restaurant, likeRestaurant, ipAddress);
    }


    @Override
    protected String getTableName() {
        return "LIKES";
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return "NUMERO";
    }

    @Override
    protected void setInsertParameters(BasicEvaluation obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.isLikeRestaurant() ? "T" : "F");
        statement.setDate(2, java.sql.Date.valueOf(obj.getVisitDate()));
        statement.setString(3, obj.getIpAddress());
        Integer restaurantId = extractPrimaryKey(obj.getRestaurant());
        statement.setInt(4, restaurantId);
        statement.setInt(5, obj.getId());
    }

    @Override
    protected void setUpdateParameters(BasicEvaluation obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.isLikeRestaurant() ? "T" : "F");
        statement.setDate(2, java.sql.Date.valueOf(obj.getVisitDate()));
        statement.setString(3, obj.getIpAddress());
        Integer restaurantId = extractPrimaryKey(obj.getRestaurant());
        statement.setInt(4, restaurantId);
        statement.setInt(5, obj.getId());
    }

    @Override
    protected String generateInsertStatement() {
        return "(APPRECIATION, DATE_EVAL, ADRESSE_IP, FK_REST) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        // Assuming that the primary key and foreign keys cannot be updated
        return "APPRECIATION = ?, DATE_EVAL = ?, ADRESSE_IP = ? WHERE NUMERO = ?";
    }

    @Override
    protected String getNameColumnName() {
        return "APPRECIATION";
    }
}
