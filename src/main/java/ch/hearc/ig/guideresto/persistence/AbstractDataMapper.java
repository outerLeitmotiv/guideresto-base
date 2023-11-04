package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.persistence.OracleDBConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDataMapper<T> implements DataMapper<T> {
    protected Connection connection;
    protected Map<Integer, T> cache = new HashMap<>();

    public AbstractDataMapper() {
        this.connection = OracleDBConnection.getInstance();
    }
    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    @Override
    public T findById(Integer id) throws DataMapperException {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName() + " WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                T entity = mapResultSetToEntity(resultSet);
                cache.put(id, entity);
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error retrieving entity");
        }

        return null;
    }
    @Override
    public void insert(T obj) throws DataMapperException {
        // Placeholder implementation
        throw new UnsupportedOperationException("Insert not implemented");
    }
    @Override
    public T update(T obj) throws DataMapperException {
        // Placeholder implementation
        throw new UnsupportedOperationException("Update not implemented");
    }
    @Override
    public void delete(T obj) throws DataMapperException {
        // Placeholder implementation
        throw new UnsupportedOperationException("Delete not implemented");
    }
    @Override
    public List<T> findAll() throws DataMapperException {
        // Placeholder implementation
        throw new UnsupportedOperationException("FindAll not implemented");
    }
    protected abstract String getTableName();
}
