#include "matching_brackets.h"
#include <stack>

namespace matching_brackets
{
bool check(const std::string &s)
{
    std::stack<char> stack;
    for (char ch : s)
    {
        if (ch == '(' || ch == '[' || ch == '{')
        {
            stack.push(ch);
        }
        else if (ch == ')' || ch == ']' || ch == '}')
        {
            if (stack.empty() ||
                (ch == ')' && stack.top() != '(') ||
                (ch == ']' && stack.top() != '[') ||
                (ch == '}' && stack.top() != '{'))
            {
                return false;
            }
            else
            {
                stack.pop();
            }
        }
    }

    return stack.empty();
}
} // namespace matching_brackets
