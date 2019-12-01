#include "triangle.h"
#include <array>
#include <stdexcept>

using namespace std;

namespace triangle
{
flavor kind(double a, double b, double c)
{
    array<double, 3> sides = {a, b, c};
    sort(sides.begin(), sides.end());
    if (sides[0] <= 0 || sides[0] + sides[1] <= sides[2])
    {
        throw domain_error("Invalid triangle!");
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
