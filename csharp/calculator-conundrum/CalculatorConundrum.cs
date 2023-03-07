using System;

public static class SimpleCalculator
{
    public static string Calculate(int operand1, int operand2, string operation)
    {
        if (operation == null)
        {
            throw new ArgumentNullException();
        }

        int outcome;
        if (operation.Equals("+"))
        {
            outcome = operand1 + operand2;
        }
        else if (operation.Equals("*"))
        {
            outcome = operand1 * operand2;
        }
        else if (operation.Equals("/"))
        {
            if (operand2 == 0)
            {
                return "Division by zero is not allowed.";
            }

            outcome = operand1 / operand2;
        }
        else if (operation.Equals(""))
        {
            throw new ArgumentException();
        }
        else
        {
            throw new ArgumentOutOfRangeException();
        }

        return $"{operand1} {operation} {operand2} = {outcome}";
    }
}
