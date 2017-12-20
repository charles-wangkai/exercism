def two_bucket(bucket_one_cap, bucket_two_cap, desired_liters, first):
    buckets = [Bucket('one', bucket_one_cap), Bucket('two', bucket_two_cap)]
    if first == 'two':
        buckets = buckets[::-1]

    move = 0
    while True:
        move += 1

        if buckets[0].liter == 0:
            buckets[0].liter = buckets[0].cap
        elif buckets[1].cap == desired_liters:
            buckets[1].liter = buckets[1].cap
        elif buckets[1].liter == buckets[1].cap:
            buckets[1].liter = 0
        else:
            transfer = min(buckets[0].liter, buckets[1].cap - buckets[1].liter)
            buckets[0].liter -= transfer
            buckets[1].liter += transfer

        if buckets[0].liter == desired_liters:
            return move, buckets[0].label, buckets[1].liter
        elif buckets[1].liter == desired_liters:
            return move, buckets[1].label, buckets[0].liter


class Bucket:
    def __init__(self, label, cap):
        self.label = label
        self.cap = cap
        self.liter = 0
