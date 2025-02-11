package com.csc.information_system;

import java.sql.*;

/**
 * DataBaseClass is a class to interract with Databse like:
 * creating database, querying
 */
public class DataBaseClass {

    private Connection connection;
    private String url = "jdbc:sqlite:my.db";
    private static DataBaseClass dataBaseClass;

    private DataBaseClass() {
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("connection established");
                String createTableSQL = "CREATE TABLE IF NOT EXISTS commands " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Command STRING, " +
                        "Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);";
                Statement createTableStmt = connection.createStatement();
                createTableStmt.execute(createTableSQL);
                System.out.println("Table created or exists.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static DataBaseClass getInstance() {
        if (dataBaseClass == null) {
            dataBaseClass = new DataBaseClass();
        }
        return dataBaseClass;
    }

    /** This method is for getting data from db */
    public void getFromDB() {
        try {
            Statement stmt = connection.createStatement();
            String selectSQL = "SELECT * FROM commands";
            ResultSet rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                System.out.println(rs.getString("Timestamp") + " " + rs.getString("Command"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is for adding data to db.
     * @param input is a prepared string from the console.
     */
    public void insertToDB(String input) {
        try {
            Statement stmt = connection.createStatement();
            String insertSQL = "INSERT INTO commands (Command) VALUES ('"+input+"');";
            stmt.execute(insertSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
