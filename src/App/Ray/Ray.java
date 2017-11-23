package App.Ray;

import App.util.Vec3;

public class Ray
{
  public Vec3 origin;
  public Vec3 direction;
  public Ray(Vec3 o, Vec3 d)
  {
    origin = o;
    direction = d;
  }
}
