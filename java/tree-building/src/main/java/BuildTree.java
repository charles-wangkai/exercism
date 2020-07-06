import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BuildTree {
	TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
		if (records.isEmpty()) {
			return null;
		}

		List<Record> sortedRecords = records.stream()
				.sorted((record1, record2) -> Integer.compare(record1.getRecordId(), record2.getRecordId()))
				.collect(Collectors.toList());

		if (IntStream.range(0, sortedRecords.size()).anyMatch(i -> sortedRecords.get(i).getRecordId() != i
				|| sortedRecords.get(i).getRecordId() < sortedRecords.get(i).getParentId())) {
			throw new InvalidRecordsException("Invalid Records");
		}

		Map<Integer, TreeNode> idToTreeNode = new HashMap<>();
		for (Record record : sortedRecords) {
			int currentId = record.getRecordId();
			int parentId = record.getParentId();

			TreeNode current = findTreeNode(idToTreeNode, currentId);
			TreeNode parent = findTreeNode(idToTreeNode, parentId);

			if (currentId != parentId) {
				parent.getChildren().add(current);
			}
		}

		return idToTreeNode.get(0);
	}

	private TreeNode findTreeNode(Map<Integer, TreeNode> idToTreeNode, int id) {
		if (!idToTreeNode.containsKey(id)) {
			idToTreeNode.put(id, new TreeNode(id));
		}

		return idToTreeNode.get(id);
	}
}
