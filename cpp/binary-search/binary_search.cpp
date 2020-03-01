#include "binary_search.h"

namespace binary_search
{
std::size_t find(const std::vector<int> &data, int target)
{
    auto p{std::equal_range(data.begin(), data.end(), target)};
    if (p.first == p.second)
    {
        throw std::domain_error{"Not found"};
    }

    return p.first - data.begin();
}
} // namespace binary_search
