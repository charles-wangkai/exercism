#if !defined(CLOCK_H)
#define CLOCK_H

#include <string>

namespace date_independent
{
class clock
{
private:
    int total_minute;

    clock(int total_minute);

public:
    operator std::string() const;
    static clock at(int hour, int minute);
    clock plus(int minute);
    friend bool operator==(const clock &clock1, const clock &clock2);
    friend bool operator!=(const clock &clock1, const clock &clock2);
};
} // namespace date_independent

#endif // CLOCK_H