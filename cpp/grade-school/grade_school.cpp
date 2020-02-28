#include "grade_school.h"

namespace grade_school
{
void school::add(const std::string &name, int grade)
{
    name_to_grade[name] = grade;
}

std::vector<std::string> school::grade(int grade) const
{
    std::vector<std::string> result;
    for (const auto &it : name_to_grade)
    {
        if (it.second == grade)
        {
            result.push_back(it.first);
        }
    }

    std::sort(result.begin(), result.end());

    return result;
}

std::map<int, std::vector<std::string>> school::roster() const
{
    std::map<int, std::vector<std::string>> grade_to_names;
    for (const auto &it : name_to_grade)
    {
        if (grade_to_names.count(it.second) == 0)
        {
            grade_to_names[it.second] = grade(it.second);
        }
    }

    return grade_to_names;
}
} // namespace grade_school
