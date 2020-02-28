#include "sum_of_multiples.h"
#include <algorithm>

namespace sum_of_multiples
{
int to(const std::initializer_list<int> &multiples, int limit)
{
    int result{0};
    for (int i{1}; i < limit; ++i)
    {
        if (std::any_of(multiples.begin(), multiples.end(), [&](int multiple) { return i % multiple == 0; }))
        {
            result += i;
        }
    }

    return result;
}
} // namespace sum_of_multiples
