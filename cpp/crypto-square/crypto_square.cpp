#include "crypto_square.h"
#include <cmath>

namespace crypto_square
{
cipher::cipher(const std::string &plain_text) : plain_text{plain_text} {}

std::string cipher::normalize_plain_text()
{
    std::string result;
    for (char ch : plain_text)
    {
        if (std::isalnum(ch))
        {
            result += std::tolower(ch);
        }
    }

    return result;
}

std::string::size_type cipher::get_square_size()
{
    return std::ceil(std::sqrt(normalize_plain_text().length()));
}

std::vector<std::string> cipher::plain_text_segments()
{
    std::string normalized_plain_text{normalize_plain_text()};
    std::string::size_type square_size{get_square_size()};

    std::vector<std::string> result;
    for (std::string::size_type i{0}; i < normalized_plain_text.length(); i += square_size)
    {
        result.push_back(normalized_plain_text.substr(i, square_size));
    }

    return result;
}

std::string cipher::cipher_text()
{
    std::string result;
    std::string::size_type square_size{get_square_size()};
    std::vector<std::string> segments{plain_text_segments()};
    for (std::string::size_type i{0}; i < square_size; ++i)
    {
        for (const auto &segment : segments)
        {
            if (i < segment.length())
            {
                result += segment[i];
            }
        }
    }

    return result;
}

std::string cipher::normalized_cipher_text()
{
    std::string result;
    std::string::size_type square_size{get_square_size()};
    std::vector<std::string> segments{plain_text_segments()};
    for (std::string::size_type i{0}; i < square_size; ++i)
    {
        if (i != 0)
        {
            result += ' ';
        }

        for (const auto &segment : segments)
        {
            if (i < segment.length())
            {
                result += segment[i];
            }
            else
            {
                result += ' ';
            }
        }
    }

    return result;
}
} // namespace crypto_square
