package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Grade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDataMapper extends AbstractDataMapper<Grade>{
    @Override
    protected Grade mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("NUMERO");
        Integer gradeValue = resultSet.getInt("NOTE");
        CompleteEvaluation evaluation = new CompleteEvaluationDataMapper().findById(resultSet.getInt("FK_COMM"));
        EvaluationCriteria criteria = new EvaluationCriteriaDataMapper().findById(resultSet.getInt("FK_CRIT"));
        return new Grade(id, gradeValue, evaluation, criteria);
    }

    @Override
    protected String getTableName() {
        return "NOTES";
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return "NUMERO";
    }

    @Override
    protected void setInsertParameters(Grade obj, PreparedStatement statement) throws SQLException {
        statement.setInt(1, obj.getGrade());
        statement.setInt(2, extractPrimaryKey(obj.getEvaluation()));
        statement.setInt(3, extractPrimaryKey(obj.getCriteria()));
    }

    @Override
    protected void setUpdateParameters(Grade obj, PreparedStatement statement) throws SQLException {
        setInsertParameters(obj, statement);
        statement.setInt(4, extractPrimaryKey(obj));
    }

    @Override
    protected String generateInsertStatement() {
        return "(NOTE, FK_COMM, FK_CRIT) VALUES (?, ?, ?)";
    }

    @Override
    protected String generateUpdateStatement() {
        return "NOTE = ?, FK_COMM = ?, FK_CRIT = ? WHERE NUMERO = ?";
    }

    @Override
    protected String getNameColumnName() {
        return "NOTE";
    }
}
