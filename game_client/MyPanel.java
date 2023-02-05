import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel
{
    public MyPanel()
    {
        super.setBackground(Color.white);
        super.setPreferredSize(new Dimension(640,480));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}