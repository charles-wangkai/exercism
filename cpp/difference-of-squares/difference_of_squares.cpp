#include "difference_of_squares.h"

namespace difference_of_squares
{
int square_of_sum(int n)
{
    int sum{n * (n + 1) / 2};

    return sum * sum;
}

int sum_of_squares(int n)
{
    int result{0};
    for (int i{1}; i <= n; ++i)
    {
        result += i * i;
    }

    return result;
}

int difference(int n)
{
    return square_of_sum(n) - sum_of_squares(n);
}
} // namespace difference_of_squares
