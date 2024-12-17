package utils;

import java.sql.*;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static Connection connection;

    // Load database properties and establish a connection
    public static void connectToDatabase(String connectString) {
//        Properties props = new Properties();
        try {
            // Load properties from the configuration file
//            props.load(Utils.class.getClassLoader().getResourceAsStream(connectString));

            String hostname = System.getenv("DB_HOSTNAME");
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");
            String dbname = System.getenv("DB_NAME");
            String port = System.getenv("DB_PORT");
//            String hostname = props.getProperty("db.hostname");
//            String username = props.getProperty("db.username");
//            String password = props.getProperty("db.password");
//            String dbname = props.getProperty("db.dbname");
//            String port = props.getProperty("db.port");
            if (hostname == null || username == null || password == null || dbname == null || port == null) {
                throw new RuntimeException("Environment variables not set!");
            }

            System.out.println("hostname Connection String: " + hostname);
            System.out.println("username Connection String: " + username);
            System.out.println("password Connection String: " + password);
            System.out.println("dbname Connection String: " + dbname);
            System.out.println("port Connection String: " + port);
            // Build the JDBC connection string
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbname;
            System.out.println("Database Connection String: " + jdbcUrl);

            // Load PostgreSQL driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection and store it in the static variable
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found: " + e.toString());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred: " + e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.toString());
            e.printStackTrace();
        }
    }

    // Execute a SELECT query and return the result
    public static ResultSet executeQuery(String query) throws Exception {
        if (connection == null) {
            throw new IllegalStateException("Database connection is not established. Call connectToDatabase() first.");
        }

        System.out.println("Executing Query: " + query);
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        return result;
    }

    // Close the database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close database connection: " + e.toString());
            }
        }
    }
}
