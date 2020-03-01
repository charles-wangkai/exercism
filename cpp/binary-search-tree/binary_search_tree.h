#if !defined(BINARY_SEARCH_TREE_H)
#define BINARY_SEARCH_TREE_H

#include <memory>
#include <vector>

namespace binary_search_tree
{
template <class T>
class binary_tree
{
private:
    T data_;
    std::unique_ptr<binary_tree> left_;
    std::unique_ptr<binary_tree> right_;
    std::vector<T> sorted;

    void inorder(binary_tree *root)
    {
        if (left_)
        {
            left_->inorder(root);
        }

        root->sorted.push_back(data_);

        if (right_)
        {
            right_->inorder(root);
        }
    }

public:
    binary_tree(const T &data) : data_{data} {}

    const T &data() { return data_; }

    const std::unique_ptr<binary_tree> &left() { return left_; }

    const std::unique_ptr<binary_tree> &right() { return right_; };

    void insert(const T &data)
    {
        if (data <= this->data_)
        {
            if (left_)
            {
                left_->insert(data);
            }
            else
            {
                left_ = std::make_unique<binary_tree>(data);
            }
        }
        else
        {
            if (right_)
            {
                right_->insert(data);
            }
            else
            {
                right_ = std::make_unique<binary_tree>(data);
            }
        }
    }

    typename std::vector<T>::iterator begin()
    {
        sorted.clear();
        inorder(this);

        return sorted.begin();
    }

    typename std::vector<T>::iterator end()
    {
        return sorted.end();
    }
};
} // namespace binary_search_tree

#endif // BINARY_SEARCH_TREE_H