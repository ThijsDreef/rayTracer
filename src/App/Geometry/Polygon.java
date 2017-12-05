package App.Geometry;

import App.Ray.Ray;
import App.util.Vec3;

public class Polygon extends Primitive
{
  Vec3[] points;
  Plane plane;
  public Polygon(Vec3[] points, Vec3 pos, Vec3 normal, Vec3 matColor)
  {
    this.matColor = matColor;
    this.plane = new Plane(pos, normal, matColor);
    this.points = points;
    this.pos =  pos;
    this.normal = normal;
  }
  @Override
  public double intersect(Ray ray)
  {
    double t = plane.intersect(ray);
    if (t > 0)
      if (pip(ray.origin.plus(ray.direction.times(t))))
        return t;
    return 0;
  }

  @Override
  public Vec3 getNormal(Vec3 hit)
  {
    return normal;
  }
  public boolean pip(Vec3 point)
  {
    if (normal.y != 0)
    {
      point.y = point.z - pos.z;
      point.x -= pos.x;
    }
    else if (normal.z != 0)
    {
      point.y -= pos.y;
      point.x -= pos.x;
    }
    else if (normal.x != 0)
    {
      point.y -= pos.y;
      point.x = point.z - pos.z;
    }
    int found = search(0, points.length -1, point);
    if (found == 0) return false;
    return inTriangle(points[0], points[found], points[found+1], point);
  }
  private boolean isLeft(Vec3 a, Vec3 b, Vec3 c)
  {
    return a.crossProduct(b, c) > -0.0001;
  }
  private boolean inTriangle(Vec3 a, Vec3 b, Vec3 c, Vec3 x)
  {
    return (isLeft(x,a,b) == isLeft(x,b,c) && isLeft(x,b,c) == isLeft(x,c,a));
  }
  private int search(int left, int right, Vec3 point)
  {
    int i = left;
    int j = right;
    while (i < j-1)
    {
      int mid = (i+j)/2;
      if (isLeft(points[0], points[mid], point)) i = mid;
      else j = mid;
    }
    return i;
  }
}
