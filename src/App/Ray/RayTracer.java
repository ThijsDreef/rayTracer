package App.Ray;

import App.Geometry.Primitive;
import App.Window;
import App.util.Vec3;

import java.awt.image.DataBufferInt;

public class RayTracer
{
  private Vec3 eye = new Vec3(0.0f, 0.0f, -1.5f);
  private int[] pixels;
  private double[] depthMap;
  int width, height;
  public RayTracer(Window window)
  {
    pixels = ((DataBufferInt) window.getImage().getRaster().getDataBuffer()).getData();
    width = window.getWidth();
    height = window.getHeight();
    depthMap = new double[pixels.length];
    for (int i = 0; i < pixels.length; i++)
    {
      pixels[i] = 0xff000000;
      depthMap[i] = 0xffffffff;
    }
  }
  public void setPixels(int x, int y, int color)
  {
    pixels[x + y * width] = color;
  }
  public void clear()
  {
    for (int x = 0; x < width; x++)
      for (int y = 0; y < height; y++)
        pixels[x + y * width] = 0xff000000;
  }
  public void tracePrimitives(Primitive[] primitives)
  {
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        Ray ray = new Ray(eye, new Vec3(x / (float)width, y / (float)height, 1).normalize());
        for (int i = 0; i < primitives.length; i++)
        {
          double t = primitives[i].intersect(ray);
          if (t != 0)
          {
            Vec3 distance = (ray.origin.times(ray.direction.times(t)));
            double color = (distance.length() * distance.length() / 600);
            color = 1 - color;
            color = clamp(color, 0.1, 1);
            setPixels(x, y, primitives[i].matColor.times(color).convertToColor());
          }
        }
      }
    }
  }
  public double clamp(double clamp, double low, double high)
  {
    if (clamp > high)
      clamp = high;
    else if (clamp < low)
      clamp = low;
    return clamp;
  }

}
