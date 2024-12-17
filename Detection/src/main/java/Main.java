import config.DatabaseConnection;
import services.DetectionService;
import services.LogsService;
import services.VideoService;
import views.CameraUI;
import controllers.CameraController;
import javax.swing.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Error: No se puede abrir la cámara.");
            return;
        }

        CameraUI cameraUI = new CameraUI();
        DetectionService detectionService = new DetectionService("Detection\\" +
                "src\\main\\resources\\haarcascade_frontalface_alt.xml");

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        LogsService logsService = new LogsService(databaseConnection);
        VideoService videoService = new VideoService(databaseConnection);
        CameraController cameraController = new CameraController(detectionService, cameraUI, logsService, videoService);

        JFrame frame = new JFrame("Detección de Personas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.add(cameraUI);
        frame.setVisible(true);

        Mat videoFrame = new Mat();

        while (capture.read(videoFrame)) {
            cameraController.processFrame(videoFrame);
        }

        capture.release();
    }
}

