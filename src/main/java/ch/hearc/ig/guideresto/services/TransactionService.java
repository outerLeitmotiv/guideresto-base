package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.persistence.OracleDBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;

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
            System.out.println("Committing transaction...");
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("Transaction committed.");
        }
    }

    public void rollbackTransaction() throws SQLException {
        Connection connection = OracleDBConnection.getInstance();
        if (!connection.isClosed()) {
            System.out.println("Rolling back transaction...");
            connection.rollback();
            connection.setAutoCommit(true);
            System.out.println("Transaction rolled back.");
        }
    }
}
