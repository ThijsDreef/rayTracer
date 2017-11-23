package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 22/11/2017.
 */
public class Sphere extends Primitive
{
  public Vec3 pos;
  float radius;
  double xSpeed = 0.02;
  double ySpeed = 0.02;
  public Sphere(Vec3 pos, float radius, Vec3 color)
  {
    matColor = color;
    this.pos = pos;
    this.radius = radius;
  }

  @Override
  public double intersect(Ray ray)
  {
      Vec3 o = ray.origin;
      Vec3 d = ray.direction;
      Vec3 oc = o.minus(pos);
      double b = 2 * Vec3.dot(oc, d);
      double c = Vec3.dot(oc, oc) - radius*radius;
      double disc = b*b - 4 * c;
      if (disc < 1e-4) return 0;
      disc = Math.sqrt(disc);
      double t0 = -b - disc;
      double t1 = -b + disc;
      double t = (t0 < t1) ? t0 : t1;
      if (t < 0)
        return 0;
      return t;

  }

  public void bounce(int width, int height)
  {
    if (pos.x > width || pos.x < 0)
    {
      xSpeed = -xSpeed;
    }
    if (pos.y > height || pos.y < 0)
    {
      ySpeed = -ySpeed;
    }
    pos.x += xSpeed;
    pos.y += ySpeed;
  }

}
