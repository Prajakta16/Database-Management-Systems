package edu.northeastern.cs5200;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://cs5200-spring2020-dharme.cw4ymzsbp1tf.us-east-2.rds.amazonaws.com/cs5200-spring2020-dharme?useSSL=false";
    private static final String USER = "pdharme";
    private static final String PASSWORD = "1qa2ws#ED";
    private static 	java.sql.Connection dbConnection = null;


    public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException { //added java.sql.Connection instead of just Connection
        Class.forName(DRIVER);
        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            return  dbConnection;
        } else { return  dbConnection; } }

    public static void closeConnection() {
        try {
            if (dbConnection != null) {
                dbConnection.close();
                dbConnection = null; }
        } catch (SQLException e) {
            System.out.println("Problem closing..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

