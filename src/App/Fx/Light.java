package App.Fx;

import App.Geometry.Primitive;
import App.Ray.Ray;
import App.util.Util;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 26/11/2017.
 */
public class Light
{
  private Vec3 pos;
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
      if (primitives[i].intersect(ray) > 0)
        shadowBias -= 0.2;
    }
    return Util.clamp(Vec3.dot(pos.minus(hit).normalize(), normal.normalize()) * shadowBias, 0, 1);
  }
}
