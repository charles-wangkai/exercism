using System;
using System.Linq;

public static class Identifier
{
    public static string Clean(string identifier) =>
        String.Concat(
            String
                .Concat(
                    identifier.Select(
                        (c, index) =>
                        {
                            if (c == ' ')
                            {
                                return "_";
                            }
                            if (Char.IsControl(c))
                            {
                                return "CTRL";
                            }
                            if (index != 0 && identifier[index - 1] == '-')
                            {
                                return Char.ToUpper(c).ToString();
                            }

                            return c.ToString();
                        }
                    )
                )
                .Where(c => (c == '_' || Char.IsLetter(c)) && !(c >= 'α' && c <= 'ω'))
        );
}
