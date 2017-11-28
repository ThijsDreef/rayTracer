package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 27/11/2017.
 */
public class Plane extends Primitive
{
  int width, height;
  public Plane(Vec3 pos, Vec3 normal, Vec3 color, int width, int height)
  {
    this.width = width;
    this.height = height;
    this.matColor = color;
    this.pos = pos;
    this.normal = normal;
  }
  @Override
  public double intersect(Ray ray)
  {
    double denom = Vec3.dot( normal, ray.direction);
    if( denom > 0.0f )
    {
      double t = Vec3.dot(normal, pos.minus(ray.origin)) / denom;
      Vec3 hit = ray.origin.times(ray.direction.times(t));
      if (hit.x > pos.x - width && hit.x < pos.x + width && hit.z > pos.z - height && hit.z < pos.z + height)
        return t;
    }
    return 0;
  }

  @Override
  public Vec3 getNormal(Vec3 hit)
  {
    return normal.minus(hit);
  }
}
