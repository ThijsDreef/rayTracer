package App.Ray;

import App.Fx.Light;
import App.Geometry.Primitive;
import App.Window;
import App.util.Vec3;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class RayTracer
{
  private Vec3 eye = new Vec3(0.0f, 0.0f, -1.5);
  private float perspectiveCorrect;
  private int[] pixels;
  private boolean multiThread = false;
  private int amountOfThreads;
  private final float maxRenderDistance = 30.0f;
  private int width, height;
  int done = 0;
  private RayTraceThread[] threads;
  ArrayList<Light> lights;
  public RayTracer(Window window, boolean multiThread, int amountOfThreads)
  {
    lights = new ArrayList<>();
    this.amountOfThreads = amountOfThreads;
    this.multiThread = multiThread;
    pixels = ((DataBufferInt) window.getImage().getRaster().getDataBuffer()).getData();
    width = window.getWidth();
    height = window.getHeight();
    perspectiveCorrect = width / (float)height;
    for (int i = 0; i < pixels.length; i++)
    {
      pixels[i] = 0xff000000;
    }
    if (multiThread)
    {
      threads = new RayTraceThread[amountOfThreads];
      for (int i = 0; i < amountOfThreads; i++)
      {
        threads[i] = new RayTraceThread(width, height, i, amountOfThreads, null, this, this);
        threads[i].start();
      }
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
      {
        pixels[x + y * width] = 0xff202020;
      }
  }
  public void tracePrimitives(Primitive[] primitives)
  {
    if (!multiThread)
    {
      for (int x = 0; x < width; x++)
      {
        for (int y = 0; y < height; y++)
        {
          traceRay(x, y, primitives);
        }
      }
    }
    else
    {
      for (int i = 0; i < amountOfThreads; i++)
      {
        threads[i].primitives = primitives;
      }
      done = 0;
      synchronized (this)
      {
        this.notifyAll();
      }
      while (done < amountOfThreads)
      {
        try
        {
          Thread.sleep(0);
        } catch (InterruptedException e)
        {
          e.printStackTrace();
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
  public void traceRay(int x, int y, Primitive[] primitives)
  {
    double depth = maxRenderDistance;
    Ray ray = new Ray(eye, new Vec3((x / (float) width) - 0.5, ((y / (float) height) - 0.5) / perspectiveCorrect, 1).normalize());
    for (int i = 0; i < primitives.length; i++)
    {
      if (depth < primitives[i].pos.z)
        continue;
      double t = primitives[i].intersect(ray);
      if (t > 0)
      {
        Vec3 hit = ray.origin.plus(ray.direction.times(t));
        if (depth <= hit.z)
          continue;
        depth = hit.z;
        double color = 0;
        Vec3 normal = primitives[i].getNormal(hit).normalize();
        for (int j = 0; j < lights.size(); j++)
          color += lights.get(j).getLight(hit, primitives[i], primitives, normal);
        color = clamp(color, 0, 1);
        setPixels(x, y, primitives[i].matColor.times(color).convertToColor());
      }
    }
  }
  public void addLight(Light light)
  {
    lights.add(light);
  }
  public synchronized void done()
  {
    done++;
  }
}
