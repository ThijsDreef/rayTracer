package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 22/11/2017.
 */
public class Sphere extends Primitive
{
  float radius;
  double xSpeed = 0.02;
  double ySpeed = 0.02;
  double zSpeed = 0.02;
  int width, height;
  public Sphere(Vec3 pos, float radius, Vec3 color, int width, int height)
  {
    this.width = width;
    this.height = height;
    xSpeed = Math.random() * 0.005;
    ySpeed = Math.random() * 0.005;
    zSpeed = Math.random() * 0.005;
    matColor = color;
    this.pos = pos;
    this.radius = radius;
  }

  @Override
  public double intersect(Ray ray)
  {
    Vec3 m = ray.origin.minus(pos);
    double b = Vec3.dot(m, ray.direction);
    double c = Vec3.dot(m, m) - radius * radius;
    if (c > 0.0f && b > 0.0f) return 0;
    double discr = b * b - c;
    if (discr < 0.0) return 0;
    double t = -b - Math.sqrt(discr);
    if (t < 0.0) t = 0.0;
    return t;
  }

  public void update()
  {
    if (pos.x > width || pos.x < - width)
    {
      xSpeed = Math.random() * 0.05;
      if (pos.x < -width)
        xSpeed = -xSpeed;
      xSpeed = -xSpeed;
    }
    if (pos.y > height || pos.y < - height)
    {
      ySpeed = Math.random() * 0.05;
      if (pos.y < - height)
        ySpeed = -ySpeed;
      ySpeed = -ySpeed;
    }
    if (pos.z > 18 || pos.z < 0)
    {
      zSpeed = Math.random() * 0.05;
      if (pos.z > height)
        zSpeed = -zSpeed;
    }
    pos.x += xSpeed;
    pos.y += ySpeed;
    pos.z += zSpeed;
  }

  @Override
  public Vec3 getNormal(Vec3 hit)
  {
    return hit.minus(pos);
  }
}
