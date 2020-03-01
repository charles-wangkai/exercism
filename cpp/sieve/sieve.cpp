#include "sieve.h"

namespace sieve
{
std::vector<int> primes(int limit)
{
    std::vector<bool> primes(limit + 1, true);
    for (std::vector<bool>::size_type i{2}; i < primes.size(); ++i)
    {
        for (std::vector<bool>::size_type j{i * i}; j < primes.size(); j += i)
        {
            primes[j] = false;
        }
    }

    std::vector<int> result;
    for (std::vector<bool>::size_type i{2}; i < primes.size(); ++i)
    {
        if (primes[i])
        {
            result.push_back(i);
        }
    }

    return result;
}
} // namespace sieve
