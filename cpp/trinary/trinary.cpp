#include "trinary.h"

namespace trinary
{
int to_decimal(const std::string &s)
{
    int result{0};
    for (char ch : s)
    {
        if (ch >= '0' && ch <= '2')
        {
            result = result * 3 + ch - '0';
        }
        else
        {
            return 0;
        }
    }

    return result;
}
} // namespace trinary
