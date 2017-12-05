package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 23/11/2017.
 */
public class Primitive implements Intersectable
{
  public Vec3 matColor;
  public Vec3 pos;
  public Vec3 normal;
  @Override
  public double intersect(Ray ray)
  {
    return 0;
  }
  public void update() {}
  public Vec3 getNormal(Vec3 hit)
  {
    return normal;
  }
}
