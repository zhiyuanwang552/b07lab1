#hi
public class Polynomial
{
  double[] coefficient;
  public Polynomial()
  {
    coefficient = new double[1];
    coefficient[0] = 0;
  }
  public Polynomial(double[] given)
  {
    coefficient = new double[given.length];
    for (int i = 0; i < given.length;i++)
    {
      coefficient[i] = given[i];
    }
  }
  public Polynomial add(Polynomial given)
  {
    int max_len = Math.max(given.coefficient.length,coefficient.length);
    double [] sum = new double[max_len];
    for (int i = 0; i < max_len; i++)
    {
      if (i < coefficient.length)
      {
        sum[i] += coefficient[i];
      }
      if (i < given.coefficient.length)
      {
        sum[i] += given.coefficient[i];
      }
    }
    return new Polynomial(sum);
  }
  public double evaluate(double x)
  {
    double result = 0;
    for (int i = 0; i < coefficient.length;i++)
    {
      result += coefficient[i]*(Math.pow(x,i));
    }
    return result;
  }
  public boolean hasRoot(int x)
  {
    if (evaluate(x) == 0)
    {
      return true;
    }
    return false;
  }
}
