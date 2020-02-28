#if !defined(CRYPTO_SQUARE_H)
#define CRYPTO_SQUARE_H

#include <string>
#include <vector>

namespace crypto_square
{
class cipher
{
private:
    std::string plain_text;

    std::string::size_type get_square_size();

public:
    cipher(const std::string &plain_text);
    std::string normalize_plain_text();
    std::vector<std::string> plain_text_segments();
    std::string cipher_text();
    std::string normalized_cipher_text();
};
} // namespace crypto_square

#endif // CRYPTO_SQUARE_H