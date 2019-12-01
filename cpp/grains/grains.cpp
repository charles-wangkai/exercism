#include "grains.h"

namespace grains
{
unsigned long long square(int n)
{
    return 1ULL << (n - 1);
}

unsigned long long total()
{
    unsigned long long result = 0;
    for (int i = 1; i <= 64; i++)
    {
        result += square(i);
    }

    return result;
}
} // namespace grains
