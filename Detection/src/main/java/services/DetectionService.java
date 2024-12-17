package services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DetectionService {
    private CascadeClassifier faceDetector;
    private int previousCount = 0;

    public DetectionService(String cascadeFilePath) {
        this.faceDetector = new CascadeClassifier(cascadeFilePath);
    }

    public int detectPersons(Mat frame) {
        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(frame, faces);

        // Dibuja rectángulos rosas alrededor de las personas detectadas
        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(255, 255, 0), 2);
        }

        return faces.toArray().length;
    }
}
