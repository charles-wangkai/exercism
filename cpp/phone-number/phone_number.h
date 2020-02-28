#if !defined(PHONE_NUMBER_H)
#define PHONE_NUMBER_H

#include <string>

namespace phone_number
{
class phone_number
{
private:
    std::string area_code_;
    std::string number_;

public:
    phone_number(const std::string &s);
    operator std::string() const;
    const std::string &area_code();
    const std::string &number();
};
} // namespace phone_number

#endif // PHONE_NUMBER_H