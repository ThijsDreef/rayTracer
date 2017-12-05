package App.util;

/**
 * Created by Thijs Dreef on 22/11/2017.
 */
public class Vec3
{
  public double x, y, z;
  public Vec3(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public Vec3 normalize()
  {
    double mg = Math.sqrt(x*x + y*y + z*z);
    return new Vec3(x/mg,y/mg,z/mg);
  }
  public static double dot(Vec3 one, Vec3 two)
  {
    return  one.x * two.x + one.y * two.y + one.z * two.z;
  }
  public double length()
  {
    return Math.abs(Math.sqrt(x * x + y * y + z * z));
  }
  public Vec3 minus(Vec3 other)
  {
    return new Vec3(x - other.x, y - other.y, z - other.z);
  }
  public Vec3 minus(double other)
  {
    return new Vec3(x - other, y - other, z - other);
  }
  public Vec3 times(Vec3 other) {return new Vec3(x * other.x, y * other.y, z * other.z);}
  public Vec3 times(double other) {return new Vec3(x * other, y * other, z * other);}
  public Vec3 plus(Vec3 other) {return new Vec3(x + other.x, y + other.y, z + other.z);}
  public Vec3 divide(Vec3 other) {return new Vec3(x / other.x, y / other.y, z / other.z);}
  public Vec3 divide(double other) {return new Vec3(x / other, y / other, z / other);}
  public double crossProduct(Vec3 b, Vec3 c)
  {
    return ((b.x - x) * (c.y - y) - (b.y - y) * (c.x - x));
  }
  public Vec3 rotateZ(int angle)
  {
    return new Vec3(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle), z);
  }
  public Vec3 rotateY(int angle)
  {
    return new Vec3(x * Math.cos(angle) + z * Math.sin(angle), y, -x * Math.sin(angle) + z * Math.cos(angle));
  }
  public Vec3 rotateX(int angle)
  {
    return new Vec3(x, y * Math.cos(angle) - z * Math.sin(angle), y * Math.sin(angle) + z * Math.cos(angle));
  }

  public int convertToColor()
  {
    return ((int)(1 * 255f + 0.5f) << 24 |
            (int)(x * 255f + 0.5f) << 16 |
            (int)(y * 255f + 0.5f) << 8  |
            (int)(z * 255f + 0.5f));
  }

}
