package App.Fx;

import App.Geometry.Primitive;
import App.util.Vec3;

/**
 * Created by Thijs Dreef on 26/11/2017.
 */
public class DirectionalLight extends Light
{
  Vec3 dir;
  public DirectionalLight(Vec3 dir)
  {
    this.dir = dir;
  }
  @Override
  public double getLight(Vec3 hit, Primitive hitPrimitive, Primitive[] primitives, Vec3 normal)
  {
    return Vec3.dot(dir, normal);
  }
}
