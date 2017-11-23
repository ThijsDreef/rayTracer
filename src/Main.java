import App.Geometry.Sphere;
import App.Ray.RayTracer;
import App.Window;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 22/11/2017.
 */
public class Main
{
  public static void main(String[] args)
  {
    Window window = new Window("raytrace", 860, 860);
    RayTracer rayTracer = new RayTracer(window);
    Sphere[] primitives = new Sphere[3];
    for (int i = 0; i < primitives.length; i++)
      primitives[i] = new Sphere(new Vec3((float)Math.random() * 6, (float)Math.random() * 6, 6 + ((float)Math.random() * 2 -1)), 1, new Vec3(Math.random(), Math.random(), Math.random()));
//    primitives[0] = new Sphere(new Vec3(0, 2, 6), 0.5f, new Vec3(1, 0.5, 0));
//    primitives[1] = new Sphere(new Vec3(1, 1.5, 6), 0.7f, new Vec3(1, 1, 0.5));
//    primitives[2] = new Sphere(new Vec3(1.5, 2.5, 6), 0.8f, new Vec3(1, 1, 0.8));
//    primitives[3] = new Sphere(new Vec3(2.5, 1, 6), 1, new Vec3(1, 0.2, 0));

    double time = System.nanoTime() / 1000000000.0;
    double timePassed = 0;
    int frames = 0;
    while (true)
    {
      timePassed += (System.nanoTime() / 1000000000.0) - time;
      time = System.nanoTime() / 1000000000.0;
      if (timePassed > 1)
      {
        System.out.println("one second passed frames: " + frames);
        timePassed = 0;
        frames = 0;
      }
      for (int i = 0; i < primitives.length; i++)
        primitives[i].bounce(6, 6);
      rayTracer.clear();
      rayTracer.tracePrimitives(primitives);
      window.update();
      frames++;
    }
  }
}
