using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyCoolMath
{
    public class Math
    {
        public string DivideOnZero = "dividing on zero";
        public double Add(double i, double j)
        {
            return i + j;
        }

        public double Substract(double i, double j)
        {
            return i + j;
        }

        public double Multiplay(double i, double j)
        {
            return i * j;
        }

        /*public string Divide(double i, double j)
        {
            if (j == 0)
            {
                return "inf";
            }
            return (i / j).ToString("F3");
        }*/
        public double Divide(double x, double y)
        {
            if (y == 0)
                throw new System.DivideByZeroException(DivideOnZero);
            return x / y;
        }
    }
}
