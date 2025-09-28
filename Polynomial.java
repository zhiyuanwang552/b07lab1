import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
  public Polynomial(File f)
  {
    try (Scanner sc = new Scanner(f);)
    {
      int ind = 0;
      String line = sc.nextLine();
      String[] comp = line.split("(?=[+-])");
      coefficient = new double[comp.length];
      exponent = new int[comp.length];
      for(String term : comp)
      {
        if(term.contains("x"))
        {
          String[] split = term.split("(?=[x])|(?<=x)");
          if (split.length == 2)
          {
            if(split[1].equals("x"))
            {
              coefficient[ind] = Double.parseDouble(split[0]);
              exponent[ind] = 1;
            }
            else
            {
              coefficient[ind] = 1;
              exponent[ind] = Integer.parseInt(split[1]);
            }
          }
          else
          {
            coefficient[ind] = Double.parseDouble(split[0]);
            exponent[ind] = Integer.parseInt(split[2]);
          }
        }
        else
        {
          coefficient[ind] = Double.parseDouble(term);
          exponent[ind] = 0;
        }
        ind++;
      }
    }
    catch(FileNotFoundException error)
    {
      System.out.println("File was not found");
    }

  }
  public Polynomial add(Polynomial given)
  {
    int max_len = Math.max(given.coefficient.length,coefficient.length);
    int max_exp = 0;
    for (int i = 0; i < max_len; i ++)
    {
      if (i < coefficient.length && exponent[i] > max_exp) max_exp = exponent[i];
      if (i < given.coefficient.length && given.exponent[i] > max_exp) max_exp = given.exponent[i];
    }
    double[] temp_coe = new double[max_exp+1];
    for (int i = 0; i < max_len; i ++)
    {
      if (i < coefficient.length )
      {
        temp_coe[exponent[i]] += coefficient[i]; 
      }
      if (i < given.coefficient.length)
      {
        temp_coe[given.exponent[i]] += given.coefficient[i];
      }
    }
    int len = 0;
    for (int i = 0; i < temp_coe.length; i ++)
    {
      if (temp_coe[i] != 0) len+=1;
    }
    double [] sum_coe = new double[len];
    int[] sum_exp = new int[len];
    int index = 0;
    for (int i = 0; i < temp_coe.length; i++)
    {
      if (temp_coe[i] != 0)
      {
        sum_coe[index] += temp_coe[i];
        sum_exp[index] += i;
        index++;
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
    return evaluate(x) == 0;
  }

  public Polynomial multiply(Polynomial given)
  {
    int max_exp1 = 0;
    int max_exp2 = 0;
    for (int i = 0; i < given.exponent.length; i++)
    {
      if (given.exponent[i] > max_exp1) max_exp1 = given.exponent[i];
    }
    for (int i = 0; i < exponent.length; i++)
    {
      if (exponent[i] > max_exp2) max_exp2 = exponent[i];
    }
    double[] temp_coe = new double[max_exp1 + max_exp2 + 1];
    for (int i = 0; i < given.exponent.length; i++)
    {
      for (int j = 0; j < exponent.length; j++)
      {
        temp_coe[given.exponent[i] + exponent[j]] += given.coefficient[i] * coefficient[j];
      }
    }
    int count = 0;
    for (int i = 0; i < temp_coe.length; i++)
    {
      if (temp_coe[i] != 0 ) count++;
    }
    double[] re_coe = new double[count];
    int[] re_exp = new int[count];
    count = 0;
    for (int i = 0; i < temp_coe.length; i++)
    {
      if (temp_coe[i] != 0 )
      {
        re_coe[count] = temp_coe[i];
        re_exp[count] = i;
        count++;
      }
    }
    for (int i = 0; i< re_coe.length; i++)
    {
      System.out.println("coe: "+re_coe[i]);
      System.out.println("exp: "+re_exp[i]);
    }
    return new Polynomial(re_coe,re_exp);
  }
  public void saveToFile(String path)
  {
    
    String equation = "";
    for (int i = 0; i < coefficient.length; i++)
    {
      if (exponent[i] != 0)
      {
        if (coefficient[i] == 1)
        {
          equation += "x";
        }
        else if (coefficient[i] == -1)
        {
          equation += "-x";
        }
        else
        {
          if (coefficient[i] > 0 && equation.length() != 0)
          {
            equation += '+';
          }
          equation += coefficient[i];
          equation += "x";
        }
        if (exponent[i] != 1) equation += exponent[i];
      }
      else
      {
        if(equation.length() != 0 && coefficient[i] > 0) equation += '+';
        equation += coefficient[i];
      }
    }
    try
    {
      FileWriter writer = new FileWriter(path);
      writer.write(equation);
      writer.close();
    } catch(IOException error)
    {
      System.out.println("Failed to write on file");
    }
  }
}
