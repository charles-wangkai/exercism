class Record():
    def __init__(self, record_id, parent_id):
        self.record_id = record_id
        self.parent_id = parent_id


class Node():
    def __init__(self, node_id):
        self.node_id = node_id
        self.children = []


def BuildTree(records):
    records.sort(key=lambda x: x.record_id)
    if records and (records[0].record_id != 0 or records[0].parent_id != 0 or records[-1].record_id != len(records) - 1):
        raise ValueError
    if any(map(lambda r: r.record_id < r.parent_id or (r.record_id == r.parent_id and r.record_id != 0), records)):
        raise ValueError

    trees = [Node(node_id) for node_id in range(len(records))]
    for record in records[1:]:
        trees[record.parent_id].children.append(trees[record.record_id])

    return None if len(trees) == 0 else trees[0]
