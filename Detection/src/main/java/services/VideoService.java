package services;

import config.DatabaseConnection;
import org.opencv.video.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VideoService {

    private final DatabaseConnection databaseConnection;

    public VideoService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void saveVideo(String fileName, String filePath) throws SQLException {
        String sql = "INSERT INTO videos (nombre, contenido) VALUES (?, ?)";
        try(Connection conn = this.databaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fileName);
            ps.setString(2, filePath);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
