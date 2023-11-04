package ch.hearc.ig.guideresto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OracleDBConnection {
    private static final String DB_URL = "jdbc:oracle:thin:@db.ig.he-arc.ch:1521:ens";
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            if (DB_USER == null || DB_PASSWORD == null) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter DB User: ");
                DB_USER = scanner.nextLine();

                System.out.print("Enter DB Password: ");
                DB_PASSWORD = scanner.nextLine();
            }

            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
