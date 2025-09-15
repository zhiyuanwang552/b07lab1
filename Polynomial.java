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
    coefficient = new double[given.length]
    for (int i = 0; i < given.length;i++)
    {
      coefficient[i] = given[i];
    }
  }
  public add(Polynomial given)
  {
    for (int i = 0; i < given.coefficient.length; i++)
    {
      coefficient[i] += given.coefficient[i];
    }
  } 
}
