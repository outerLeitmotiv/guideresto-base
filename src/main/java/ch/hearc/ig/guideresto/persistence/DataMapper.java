package ch.hearc.ig.guideresto.persistence;

import java.sql.Connection;
import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public interface DataMapper<T> {

    public Connection connection = OracleDBConnection.getInstance();
    public T findById(Integer id) throws DataMapperException;
    public void insert(T obj) throws DataMapperException;
    public T update(T obj) throws DataMapperException;
    public void delete(T obj) throws DataMapperException;
    public List<T> findAll() throws DataMapperException;

}
