package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.OracleDBConnection;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDataMapper<T> implements DataMapper<T> {
    protected Connection connection;
    protected Map<Integer, T> cache = new HashMap<>();

    protected AbstractDataMapper() {
        this.connection = OracleDBConnection.getInstance();
    }
    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;
    protected abstract String getTableName();
    protected abstract String getPrimaryKeyColumnName();

    @Override
    public T findByName(String name) throws DataMapperException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName() + " WHERE " + getNameColumnName() + " = ?"
            );
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer id = resultSet.getInt(getPrimaryKeyColumnName());
                if (cache.containsKey(id)) {
                    return cache.get(id);
                } else {
                    T entity = mapResultSetToEntity(resultSet);
                    cache.put(id, entity);
                    return entity;
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error retrieving entity by name: " + name);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
    public T findById(Integer id) throws DataMapperException {
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM " + getTableName() + " WHERE " + getPrimaryKeyColumnName() + "= ?"
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
        }
        return null;
    }
    @Override
    public void insert(T obj) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO " + getTableName() + " " + generateInsertStatement()
            );
            setInsertParameters(obj, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error inserting entity");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error closing statement");
                }
            }
        }
    }
    @Override
    public T update(T obj) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            Integer id = extractPrimaryKey(obj);
            if (id == null) {
                throw new DataMapperException("Cannot update entity without primary key");
            }

            T existingObj = findById(id);
            if (existingObj == null) {
                throw new DataMapperException("Entity with ID " + id + " not found");
            }

            String updateStatement = "UPDATE " + getTableName() + " SET " + generateUpdateStatement() + " WHERE " + getPrimaryKeyColumnName() + " = ?";
            statement = connection.prepareStatement(updateStatement);

            setUpdateParameters(obj, statement);
            statement.setInt(statement.getParameterMetaData().getParameterCount(), id);

            statement.executeUpdate();
            cache.put(id, obj); // Update the cache with the new object state

            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error updating entity");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error closing statement");
                }
            }
        }
    }


    @Override
    public void delete(T obj) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumnName() + " = ?"
            );
            Integer id = extractPrimaryKey(obj);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error deleting entity");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error closing statement");
                }
            }
        }
    }
    @Override
    public List<T> findAll() throws DataMapperException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName()
            );
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataMapperException("Error retrieving entities");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataMapperException("Error closing statement");
                }
            }
        }
    }
    protected abstract void setInsertParameters(T obj, PreparedStatement statement) throws SQLException;
    protected abstract void setUpdateParameters(T obj, PreparedStatement statement) throws SQLException;
    public Integer extractPrimaryKey(Object obj) {
        try {
            Field idField = obj.getClass().getDeclaredField("id"); //
            idField.setAccessible(true);
            return (Integer) idField.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // or handle the error appropriately
        }
    }

    protected abstract String generateInsertStatement();
    protected abstract String generateUpdateStatement();
    protected abstract String getNameColumnName();
}
