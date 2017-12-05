package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

public class Plane extends Primitive
{
  public Plane(Vec3 pos, Vec3 normal, Vec3 color)
  {
    this.matColor = color;
    this.pos = pos;
    this.normal = normal;
  }
  @Override
  public double intersect(Ray ray)
  {
    return Vec3.dot(normal, pos.minus(ray.origin)) / Vec3.dot(normal, ray.direction);
  }
}
