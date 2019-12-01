#include "raindrops.h"

using namespace std;

namespace raindrops
{
string convert(int n)
{
    string result;
    if (n % 3 == 0)
    {
        result += "Pling";
    }
    if (n % 5 == 0)
    {
        result += "Plang";
    }
    if (n % 7 == 0)
    {
        result += "Plong";
    }
    if (result == "")
    {
        result = to_string(n);
    }

    return result;
}
} // namespace raindrops
