import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    public Image image;
    public JFrame frame;
    public MatOfRect matOfRect;
    public ImagePanel(JFrame frame)
    {
        this.frame = frame;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public void setMatOfRect(MatOfRect matOfRect)
    {
        this.matOfRect = matOfRect;
    }

    public void paint(Graphics g)
    {
        g.drawImage(image,0,0,frame.getWidth(),frame.getHeight(),null);
        for(Rect rect : matOfRect.toArray())
        {
            g.setColor(Color.green);
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
}
