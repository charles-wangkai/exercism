#include "pascals_triangle.h"

namespace pascals_triangle
{
std::vector<std::vector<int>> generate_rows(int n)
{
    std::vector<std::vector<int>> result;
    for (int i{0}; i < n; ++i)
    {
        std::vector<int> row{1};
        for (int j{1}; j < i; ++j)
        {
            row.push_back(result[i - 1][j - 1] + result[i - 1][j]);
        }
        if (i != 0)
        {
            row.push_back(1);
        }

        result.push_back(std::move(row));
    }

    return result;
}
} // namespace pascals_triangle
