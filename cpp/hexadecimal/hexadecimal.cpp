#include "hexadecimal.h"

namespace hexadecimal
{
int convert(const std::string &s)
{
    int result{0};
    for (char ch : s)
    {
        if (ch >= '0' && ch <= '9')
        {
            result = result * 16 + ch - '0';
        }
        else if (ch >= 'a' && ch <= 'f')
        {
            result = result * 16 + ch - 'a' + 10;
        }
        else
        {
            return 0;
        }
    }

    return result;
}
} // namespace hexadecimal
