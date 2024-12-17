package controllers;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoWriter;
import services.DetectionService;
import services.LogsService;
import services.VideoService;
import views.CameraUI;

import java.sql.SQLException;

public class CameraController {
    private final DetectionService detectionService;
    private LogsService logsService;
    private VideoService videoService;
    private final CameraUI cameraUI;
    private int previousCount = 0;

    private long lastRecordedTime = 0;
    private final long videoDuration = 20000;
    private final long waitTime = 10000;
    private final long intervalLog = 5000;
    private long lastIntervalLog = 0;
    private VideoWriter videoWriter;
    private boolean isRecording = false;
    private String nameVideo;
    private String pathVideo;

    public CameraController(DetectionService detectionService, CameraUI cameraUI, LogsService logsService,
                            VideoService videoService) {
        this.detectionService = detectionService;
        this.cameraUI = cameraUI;
        this.logsService = logsService;
        this.videoService = videoService;
    }

    public void takeVideo(Mat frame) throws SQLException {
        long currentTime = System.currentTimeMillis();

        if(!isRecording && currentTime - lastRecordedTime >= waitTime){
            int codec = VideoWriter.fourcc('H', '2', '6', '4');
            double frameWidth = frame.width();
            double frameHeight = frame.height();
            nameVideo = "output_video_" + currentTime + ".mp4";
            pathVideo = "videos\\" + nameVideo;
            videoWriter = new VideoWriter(pathVideo, codec, 20.0,
                    new Size(frameWidth, frameHeight), true);
            if (!videoWriter.isOpened()){
                System.out.println("Video writer not opened");
                return;
            }
            isRecording = true;
            lastRecordedTime = currentTime;
            logsService.saveLog("Se a iniciado la grabacion");
            System.out.println("Inicio grabacion");
        }

        if(isRecording){
            videoWriter.write(frame);

            if(currentTime - lastRecordedTime >= videoDuration){
                videoWriter.release();
                isRecording = false;
                lastRecordedTime = currentTime;
                logsService.saveLog("A terminado la grabacion");
                videoService.saveVideo(nameVideo, pathVideo);
                System.out.println("Fin grabacion");
            }
        }

    }

    public void processFrame(Mat frame) throws SQLException {
        Long currentTime = System.currentTimeMillis();
        int currentCount = detectionService.detectPersons(frame);
        takeVideo(frame);

        // Actualiza el contador en la UI
        cameraUI.updatePersonCount(currentCount);
        if(currentTime - lastIntervalLog >= intervalLog){
            String log = "Se han detectado " + currentCount + " personas";
            logsService.saveLog(log);
            lastIntervalLog = currentTime;
        }

        if (currentCount > previousCount) {
            System.out.println("Persona ha entrado en el cuadro.");
        } else if (currentCount < previousCount) {
            System.out.println("Persona ha salido del cuadro.");
        }
        previousCount = currentCount;

        // Muestra la imagen en la interfaz
        cameraUI.setImage(frame);
    }
}
