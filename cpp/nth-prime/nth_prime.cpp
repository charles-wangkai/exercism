#include "nth_prime.h"
#include <stdexcept>

namespace nth_prime
{
bool is_prime(int x)
{
    for (int i{2}; i * i <= x; ++i)
    {
        if (x % i == 0)
        {
            return false;
        }
    }

    return true;
}

int next_prime(int x)
{
    while (true)
    {
        ++x;

        if (is_prime(x))
        {
            return x;
        }
    }
}

int nth(int n)
{
    if (n <= 0)
    {
        throw std::domain_error{"Invalid input!"};
    }

    int prime{1};
    for (int i{0}; i < n; ++i)
    {
        prime = next_prime(prime);
    }

    return prime;
}
} // namespace nth_prime
