#include "binary.h"

namespace binary
{
int convert(const std::string &s)
{
    int result{0};
    for (char ch : s)
    {
        if (ch == '0' || ch == '1')
        {
            result = result * 2 + ch - '0';
        }
        else
        {
            return 0;
        }
    }

    return result;
}
} // namespace binary
