#include "atbash_cipher.h"

namespace atbash_cipher
{
std::string encode(const std::string &plain_text)
{
    std::string result;
    for (char ch : plain_text)
    {
        if (std::isalnum(ch))
        {
            if (!result.empty() && result.length() % 6 == 5)
            {
                result += ' ';
            }

            if (std::isalpha(ch))
            {
                result += 'a' + 'z' - std::tolower(ch);
            }
            else
            {
                result += ch;
            }
        }
    }

    return result;
}

std::string decode(const std::string &ciphered_text)
{
    std::string result;
    for (char ch : ciphered_text)
    {
        if (std::isalpha(ch))
        {
            result += 'a' + 'z' - ch;
        }
        else if (std::isdigit(ch))
        {
            result += ch;
        }
    }

    return result;
}
} // namespace atbash_cipher
