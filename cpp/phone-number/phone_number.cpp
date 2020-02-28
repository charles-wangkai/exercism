#include "phone_number.h"

namespace phone_number
{
phone_number::phone_number(const std::string &s)
{
    std::string digits;
    for (char ch : s)
    {
        if (std::isdigit(ch))
        {
            digits += ch;
        }
    }

    std::string::size_type length{digits.length()};
    if (length != 10 && length != 11)
    {
        throw std::domain_error{"Length should be 10 or 11!"};
    }

    if (length == 11 && digits[0] != '1')
    {
        throw std::domain_error{"Phone number with length 11 should begin with 1!"};
    }

    number_ = digits.substr(length - 10);
    if (!(number_[0] >= '2' && number_[0] <= '9' && number_[3] >= '2' && number_[3] <= '9'))
    {
        throw std::domain_error{"Invalid area code or exchange code!"};
    }

    area_code_ = number_.substr(0, 3);
}

phone_number::operator std::string() const
{
    return "(" + area_code_ + ") " + number_.substr(3, 3) + "-" + number_.substr(6);
}

const std::string &phone_number::area_code()
{
    return area_code_;
}

const std::string &phone_number::number()
{
    return number_;
}
} // namespace phone_number
