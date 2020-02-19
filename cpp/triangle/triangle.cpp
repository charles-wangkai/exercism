#include "triangle.h"
#include <array>
#include <stdexcept>

namespace triangle
{
flavor kind(double a, double b, double c)
{
    std::array<double, 3> sides{a, b, c};
    std::sort(sides.begin(), sides.end());
    if (sides[0] <= 0 || sides[0] + sides[1] <= sides[2])
    {
        throw std::domain_error("Invalid triangle!");
    }

    if (sides[0] == sides[2])
    {
        return flavor::equilateral;
    }
    else if (sides[0] == sides[1] || sides[1] == sides[2])
    {
        return flavor::isosceles;
    }
    else
    {
        return flavor::scalene;
    }
}
} // namespace triangle
