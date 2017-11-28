package App.Ray;

import App.Geometry.Primitive;

public class RayTraceThread extends Thread
{
  int width, height, part, maxPart;
  float perspectiveCorrect;
  Primitive[] primitives;
  final RayTracer rayTracer;
  final Object lock;
  public RayTraceThread(int width, int height, int part, int maxPart, Primitive[] primitives, RayTracer rayTracer, Object lock)
  {

    this.lock = lock;
    this.maxPart = maxPart;
    this.rayTracer = rayTracer;
    this.width = width;
    this.height = height;
    perspectiveCorrect = width / (float)height;
    this.part = part;
    this.primitives = primitives;
  }

  @Override
  public void run()
  {
    while (true)
    {
      synchronized (lock)
      {
        try
        {
          lock.wait();
        } catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      draw(width / maxPart * part, 0, width / maxPart * (part + 1), height);
      rayTracer.done();
    }
  }

  private void draw(int startx, int starty, int width, int height)
  {
//    System.out.println(startx + " " + starty + " " + width + " " + height + " " + part + " " + maxPart) ;
    for (int x = startx; x < width; x++)
    {
      for (int y = starty; y < height; y++)
      {
        rayTracer.traceRay(x, y, primitives);
      }
    }
  }
}
