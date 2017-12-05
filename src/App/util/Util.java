package App.util;

public class Util
{
  public static double clamp(double toClamp, double low, double high)
  {
      if (toClamp > high)
        toClamp = high;
      else if (toClamp < low)
        toClamp = low;
      return toClamp;
  }
}
