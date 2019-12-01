#include "triangle.h"
#include <stdexcept>

using namespace std;

namespace triangle
{
flavor kind(double a, double b, double c)
{
    if (!(a > 0 && b > 0 && c > 0 && a + b > c && b + c > a && c + a > b))
    {
        throw domain_error("Invalid triangle!");
    }

    if (a == b && b == c)
    {
        return flavor::equilateral;
    }
    else if (a == b || b == c || c == a)
    {
        return flavor::isosceles;
    }
    else
    {
        return flavor::scalene;
    }
}
} // namespace triangle
