package App;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Thijs Dreef on 22/11/2017.
 */
public class Window extends JFrame
{
  private Canvas canvas;
  private BufferedImage image;
  private Graphics g;
  private BufferStrategy bs;
  private int width, height;

  public Window(String title, int width, int height)
  {
    super(title);
    this.width = width;
    this.height = height;
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    canvas = new Canvas();
    Dimension s = new Dimension(width, height);
    canvas.setMinimumSize(s);
    canvas.setPreferredSize(s);
    canvas.setMaximumSize(s);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    add(canvas, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(null);

    setVisible(true);

    canvas.createBufferStrategy(1);
    bs = canvas.getBufferStrategy();
    g = bs.getDrawGraphics();
  }
  public void update()
  {
    g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    bs.show();
  }
  public BufferedImage getImage() {return image;}
  public Canvas getCanvas() {return canvas;}

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }
}
