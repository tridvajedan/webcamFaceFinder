import com.github.sarxos.webcam.Webcam;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Dimension resolution = new Dimension(7680, 4320);
        String xmlFile = "D:/Sa Neta/opencv/build/etc/lbpcascades/lbpcascade_frontalface_improved.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);
        Webcam webcam = Webcam.getDefault();
        webcam.setCustomViewSizes(new Dimension[] { resolution });
        webcam.setViewSize(resolution);
        webcam.open();
        MatOfRect faceDetections = new MatOfRect();
        Mat src = Imgcodecs.imread("hello-world.png");
        classifier.detectMultiScale(src,faceDetections);
        JFrame frame = new JFrame("My frame");
        ImagePanel imagePanel = new ImagePanel(frame);
        imagePanel.setImage(webcam.getImage());
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(imagePanel);
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            src = Imgcodecs.imread("hello-world.png");
            faceDetections = new MatOfRect();
            classifier.detectMultiScale(src,faceDetections);
            System.out.println(faceDetections.toArray().length);
            ImageIO.write(webcam.getImage(),"png",new File("hello-world.png"));
            imagePanel.setImage(ImageIO.read(new File("hello-world.png")));
            imagePanel.matOfRect = faceDetections;
            imagePanel.repaint();
        }
    }
}
