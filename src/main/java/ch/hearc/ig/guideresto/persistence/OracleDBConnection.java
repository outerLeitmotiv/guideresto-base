package ch.hearc.ig.guideresto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public class OracleDBConnection {
    private static final String DB_URL = "jdbc:oracle:thin:@db.ig.he-arc.ch:1521:ens";
    private static final String DB_USER = "OLIVIER_MOUTTET";
    private static final String DB_PASSWORD = "OLIVIER_MOUTTET";
    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
