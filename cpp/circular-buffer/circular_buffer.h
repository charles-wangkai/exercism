#if !defined(CIRCULAR_BUFFER_H)
#define CIRCULAR_BUFFER_H

#include <vector>

namespace circular_buffer
{
template <class T>
class circular_buffer
{
private:
    std::vector<T> buffer;
    int head_index{0};
    int tail_index{0};
    int length{0};

    bool is_empty() { return length == 0; }

    bool is_full() { return length > 0 && head_index == tail_index; }

public:
    circular_buffer(int capacity) : buffer(capacity) {}

    T read()
    {
        if (is_empty())
        {
            throw std::domain_error{"Buffer is empty!"};
        }

        T result{buffer[head_index]};
        head_index = (head_index + 1) % buffer.size();
        --length;

        return result;
    }

    void write(const T &element)
    {
        if (is_full())
        {
            throw std::domain_error{"Buffer is full!"};
        }

        buffer[tail_index] = element;
        tail_index = (tail_index + 1) % buffer.size();
        ++length;
    }

    void clear()
    {
        head_index = 0;
        tail_index = 0;
        length = 0;
    }

    void overwrite(const T &element)
    {
        if (is_full())
        {
            read();
        }

        write(element);
    }
};
} // namespace circular_buffer

#endif // CIRCULAR_BUFFER_H