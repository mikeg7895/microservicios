package services;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LogsService {

    private final DatabaseConnection databaseConnection;

    public LogsService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void saveLog(String message) throws SQLException {
        String sql = "INSERT INTO logs (mensaje) VALUES (?)";
        try(Connection conn = databaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, message);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
