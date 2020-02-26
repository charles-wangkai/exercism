#include "hamming.h"

namespace hamming
{
int compute(const std::string &strand_a, const std::string &strand_b)
{
    if (strand_a.length() != strand_b.length())
    {
        throw std::domain_error("The strands have different length!");
    }

    int result{0};
    for (std::string::size_type i{0}; i < strand_a.length(); ++i)
    {
        if (strand_a[i] != strand_b[i])
        {
            ++result;
        }
    }

    return result;
}
} // namespace hamming
