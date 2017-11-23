package App.Geometry;

import App.Ray.Ray;

/**
 * Created by Thijs Dreef on 23/11/2017.
 */
public interface Intersectable
{
  double intersect(Ray ray);
}
