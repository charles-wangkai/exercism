import collections


def find_minimum_coins(total_change, coins):
    if total_change < 0:
        raise ValueError('Negative total change!')

    value2coins = {0: []}
    queue = collections.deque()
    queue.append(0)
    while queue:
        value = queue.popleft()

        if value == total_change:
            return value2coins[value]

        for coin in coins:
            next_value = value + coin
            if next_value <= total_change and next_value not in value2coins:
                next_coins = value2coins[value][:]
                next_coins.append(coin)

                value2coins[next_value] = next_coins
                queue.append(next_value)

    raise ValueError('Not found!')
