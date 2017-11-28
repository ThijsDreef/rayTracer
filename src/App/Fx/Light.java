package App.Fx;

import App.Geometry.Primitive;
import App.Ray.Ray;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 26/11/2017.
 */
public class Light
{
  Vec3 pos;
  public double shadowBias = 1;
  public Light()
  {
    this.pos = new Vec3(0, 0, 0);
  }
  public Light(Vec3 pos)
  {
    this.pos = pos;
  }
  public double getLight(Vec3 hit, Primitive hitPrimitive, Primitive[]primitives, Vec3 normal)
  {
    double shadowBias = 1;
    Ray ray = new Ray(hit, pos.minus(hit).normalize());

    for (int i = 0;i < primitives.length; i++)
    {
      if (hitPrimitive == primitives[i])
        continue;
      if (primitives[i].intersect(ray) > 0.1)
      {
        shadowBias -= 0.25;
      }
    }
    return Vec3.dot(pos.minus(hit).normalize(), normal) * shadowBias;
  }
}
