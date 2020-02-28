#if !defined(GRADE_SCHOOL_H)
#define GRADE_SCHOOL_H

#include <string>
#include <map>
#include <unordered_map>
#include <vector>

namespace grade_school
{
class school
{
private:
    std::unordered_map<std::string, int> name_to_grade;

public:
    void add(const std::string &name, int grade);
    std::vector<std::string> grade(int grade) const;
    std::map<int, std::vector<std::string>> roster() const;
};
} // namespace grade_school

#endif // GRADE_SCHOOL_H