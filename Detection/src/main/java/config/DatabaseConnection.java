package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/detection";
    private static final String USER = "postgres";
    private static final String PASSWORD = "valentina13";

    private static DatabaseConnection  databaseConnection;

    private DatabaseConnection(){
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Error en la conexion", e);
        }
    }

    public static synchronized DatabaseConnection getInstance(){
        if(databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection() throws SQLException {
        try{
            if (this.connection == null || this.connection.isClosed()){
               this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion", e);
        }
        return this.connection;
    }
}
