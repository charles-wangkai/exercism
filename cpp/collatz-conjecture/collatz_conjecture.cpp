#include "collatz_conjecture.h"
#include <stdexcept>

namespace collatz_conjecture
{
int steps(int n)
{
    if (n <= 0)
    {
        throw std::domain_error{"Number should be positive!"};
    }

    int step{0};
    while (n != 1)
    {
        n = (n % 2 == 0) ? (n / 2) : (3 * n + 1);
        ++step;
    }

    return step;
}
} // namespace collatz_conjecture
