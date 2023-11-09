package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.persistence.OracleDBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionService {

    // This will use the shared connection from OracleDBConnection
    public void startTransaction() throws SQLException {
        Connection connection = OracleDBConnection.getInstance();
        if (!connection.isClosed()) {
            connection.setAutoCommit(false);
        }
    }

    public void commitTransaction() throws SQLException {
        Connection connection = OracleDBConnection.getInstance();
        if (!connection.isClosed()) {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    public void rollbackTransaction() throws SQLException {
        Connection connection = OracleDBConnection.getInstance();
        if (!connection.isClosed()) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }
}
