#include "prime_factors.h"

namespace prime_factors
{
bool is_prime(int n)
{
    for (int i{2}; i * i <= n; ++i)
    {
        if (n % i == 0)
        {
            return false;
        }
    }

    return true;
}

std::vector<int> of(int n)
{
    std::vector<int> result;
    for (int i{2}; i * i <= n; ++i)
    {
        if (is_prime(i))
        {
            while (n % i == 0)
            {
                result.push_back(i);
                n /= i;
            }
        }
    }

    if (n != 1)
    {
        result.push_back(n);
    }

    return result;
}
} // namespace prime_factors
