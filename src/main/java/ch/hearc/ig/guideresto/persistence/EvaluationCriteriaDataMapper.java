package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluationCriteriaDataMapper extends AbstractDataMapper<EvaluationCriteria> {

    @Override
    protected EvaluationCriteria mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("ID");
        return new EvaluationCriteria(
                id,
                resultSet.getString("NAME"),
                resultSet.getString("DESCRIPTION")
        );
    }

    @Override
    protected String getTableName() {
        return "CRITERES_EVALUATION";
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return "NUMERO";
    }

    @Override
    protected void setInsertParameters(EvaluationCriteria obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getName());
        statement.setString(2, obj.getDescription());
    }

    @Override
    protected void setUpdateParameters(EvaluationCriteria obj, PreparedStatement statement) throws SQLException {
        statement.setString(1, obj.getName());
        statement.setString(2, obj.getDescription());
        statement.setInt(3, extractPrimaryKey(obj));
    }

    @Override
    protected String generateInsertStatement() {
        return "(NAME, DESCRIPTION) VALUES (?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "SET NAME = ?, DESCRIPTION = ? WHERE ID = ?";
    }
}
