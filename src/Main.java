import App.Fx.Light;
import App.Geometry.Plane;
import App.Geometry.Primitive;
import App.Geometry.Sphere;
import App.Ray.RayTracer;
import App.Window;
import App.util.Vec3;


public class Main
{
  public static void main(String[] args)
  {
    //enabling opengl
    System.setProperty("sun.java2d.opengl", "true");
    //window creation
    Window window = new Window("ray trace", 250, 250);
    //creating the primitives to be rendered
    Primitive[] primitives = new Primitive[30];
    for (int i = 0; i < primitives.length; i++)
      primitives[i] = new Sphere(new Vec3((float)Math.random() * 3 - 1.5, Math.random() * 3 - 1.5, Math.random() * 18  + 3), 0.5f, new Vec3(Math.random() + 0.5, Math.random() + 0.5,Math.random() + 0.5).normalize(), 3, 3);
    primitives[29] = new Plane(new Vec3(0, 4, 0), new Vec3(0, 1, 0), new Vec3(1, 1, 1), 100, 40);
    //creating the renderer enabling multithread and specifying the amount of threads to be used
    RayTracer rayTracer = new RayTracer(window, true, 8);
    //adding the lights to the scene
    rayTracer.addLight(new Light(new Vec3(0, -2, 0)));
    //setting up the timing/fps counter
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
      //updating the primitives
      for (int i = 0; i < primitives.length - 1; i++)
        primitives[i].update();
      //clear the screen
      rayTracer.clear();
      //raytrace al primitives multithreaded
      rayTracer.tracePrimitives(primitives);
      //updating the image to the window
      window.update();

      frames++;
    }
  }
}
