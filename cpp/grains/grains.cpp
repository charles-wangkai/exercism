#include "grains.h"

namespace grains
{
unsigned long long square(int n)
{
    return 1ULL << (n - 1);
}

unsigned long long total()
{
    return (1ULL << 63) - 1 + (1ULL << 63);
}
} // namespace grains
