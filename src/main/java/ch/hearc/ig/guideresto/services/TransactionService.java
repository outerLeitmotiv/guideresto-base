package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.persistence.OracleDBConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public class TransactionService {

    private Connection connection;

    public void DatabaseService() {
        this.connection = OracleDBConnection.getInstance();
    }

    public void commitTransaction() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
        }
    }

    public void rollbackTransaction() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
