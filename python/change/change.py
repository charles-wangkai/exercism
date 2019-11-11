import collections


def find_fewest_coins(coins, total_change):
    if total_change < 0:
        raise ValueError('Negative total change!')

    value_to_coins = {0: []}
    queue = collections.deque()
    queue.append(0)
    while queue:
        value = queue.popleft()

        if value == total_change:
            return value_to_coins[value]

        for coin in coins:
            next_value = value + coin
            if next_value <= total_change and next_value not in value_to_coins:
                next_coins = value_to_coins[value][:]
                next_coins.append(coin)

                value_to_coins[next_value] = next_coins
                queue.append(next_value)

    raise ValueError('Not found!')
