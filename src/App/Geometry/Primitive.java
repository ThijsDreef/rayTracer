package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 23/11/2017.
 */
public class Primitive implements Intersectable
{
  public Vec3 matColor;
  @Override
  public double intersect(Ray ray)
  {
    return 0;
  }
}
