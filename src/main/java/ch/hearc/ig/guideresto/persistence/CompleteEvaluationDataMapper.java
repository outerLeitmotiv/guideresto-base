package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompleteEvaluationDataMapper extends AbstractDataMapper<CompleteEvaluation> {
    @Override
    protected CompleteEvaluation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("NUMERO");
        LocalDate visitDate = resultSet.getDate("DATE_EVAL").toLocalDate();
        String comment = resultSet.getString("COMMENTAIRE");
        String username = resultSet.getString("NOM_UTILISATEUR");
        Restaurant restaurant = new RestaurantDataMapper().findById(resultSet.getInt("FK_REST"));
        CompleteEvaluation evaluation = new CompleteEvaluation(id, visitDate, restaurant, comment, username);
        evaluation.getGrades().addAll(new GradeDataMapper().findById(evaluation.getId()));
        return evaluation;
    }

    @Override
    protected String getTableName() {
        return "COMMENTAIRES";
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return "NUMERO";
    }

    @Override
    protected void setInsertParameters(CompleteEvaluation obj, PreparedStatement statement) throws SQLException {
        statement.setDate(1, java.sql.Date.valueOf(obj.getVisitDate()));
        statement.setString(2, obj.getComment());
        statement.setString(3, obj.getUsername());
        statement.setInt(4, extractPrimaryKey(obj.getRestaurant()));
    }

    @Override
    protected void setUpdateParameters(CompleteEvaluation obj, PreparedStatement statement) throws SQLException {
        setInsertParameters(obj, statement);
        statement.setInt(5, obj.getId());
    }

    @Override
    protected String generateInsertStatement() {
        return "(DATE_EVAL, COMMENTAIRE, NOM_UTILISATEUR, FK_REST) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "DATE_EVAL = ?, COMMENTAIRE = ?, NOM_UTILISATEUR = ? WHERE NUMERO = ?";
    }

    @Override
    protected String getNameColumnName() {
        return "COMMENTAIRE";
    }
}
