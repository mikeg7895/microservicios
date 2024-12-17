package views;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.MatOfByte;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class CameraUI extends JPanel {
    private BufferedImage image;
    private JLabel personCountLabel;

    public CameraUI() {
        setLayout(new BorderLayout());
        personCountLabel = new JLabel("Personas detectadas: 0");
        personCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        personCountLabel.setForeground(new Color(14, 117, 117));
        personCountLabel.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        personCountLabel.setOpaque(true);
        personCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(personCountLabel, BorderLayout.NORTH);
    }

    public void setImage(Mat frame) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", frame, mob);
        byte[] byteArray = mob.toArray();
        try {
            image = ImageIO.read(new ByteArrayInputStream(byteArray));
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void updatePersonCount(int count) {
        personCountLabel.setText("Personas detectadas: " + count);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}