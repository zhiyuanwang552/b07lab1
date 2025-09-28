
public class Polynomial
{
  double[] coefficient;
  int[] exponent;
  public Polynomial()
  {
    coefficient = new double[1];
    exponent = new int[1];
  }
  public Polynomial(double[] given_coe, int [] given_exp)
  {
    coefficient = new double[given_coe.length];
    exponent = new int[given_exp.length];
    for (int i = 0; i < given_coe.length;i++)
    {
      coefficient[i] = given_coe[i];
      exponent[i] = given_exp[i];
    }
  }
  public Polynomial add(Polynomial given)
  {
    int max_len = Math.max(given.coefficient.length,coefficient.length);
    double [] sum_coe = new double[max_len];
    int[] sum_exp = new int[max_len];
    for (int i = 0; i < max_len; i++)
    {
      if (i < coefficient.length)
      {
        sum_coe[i] += coefficient[i];
        sum_exp[i] += exponent[i];
      }
      if (i < given.coefficient.length)
      {
        sum_coe[i] += given.coefficient[i];
        sum_exp[i] += given.exponent[i];
      }
    }
    return new Polynomial(sum_coe,sum_exp);
  }
  public double evaluate(double x)
  {
    double result = 0;
    for (int i = 0; i < coefficient.length;i++)
    {
      result += coefficient[i]*(Math.pow(x,exponent[i]));
    }
    return result;
  }
  public boolean hasRoot(double x)
  {
    if (evaluate(x) == 0)
    {
      return true;
    }
    return false;
  }
}
