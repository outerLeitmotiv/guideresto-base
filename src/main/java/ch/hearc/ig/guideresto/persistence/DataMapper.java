package ch.hearc.ig.guideresto.persistence;
import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public interface DataMapper<T> {

    public T findByName(String name) throws DataMapperException;
    public T findById(Integer id) throws DataMapperException;
    public void insert(T obj) throws DataMapperException;
    public T update(T obj) throws DataMapperException;
    public void delete(T obj) throws DataMapperException;
    public List<T> findAll() throws DataMapperException;
    public Integer extractPrimaryKey(Object obj) throws DataMapperException;

}
