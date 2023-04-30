import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class BuildTree {
  TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
    if (records.isEmpty()) {
      return null;
    }

    List<Record> sortedRecords =
        records.stream().sorted(Comparator.comparing(Record::getRecordId)).toList();

    if (IntStream.range(0, sortedRecords.size())
        .anyMatch(
            i ->
                sortedRecords.get(i).getRecordId() != i
                    || sortedRecords.get(i).getRecordId() < sortedRecords.get(i).getParentId())) {
      throw new InvalidRecordsException("Invalid Records");
    }

    Map<Integer, TreeNode> idToTreeNode = new HashMap<>();
    for (Record record : sortedRecords) {
      int currentId = record.getRecordId();
      idToTreeNode.putIfAbsent(currentId, new TreeNode(currentId));

      int parentId = record.getParentId();
      idToTreeNode.putIfAbsent(parentId, new TreeNode(parentId));

      if (currentId != parentId) {
        idToTreeNode.get(parentId).getChildren().add(idToTreeNode.get(currentId));
      }
    }

    return idToTreeNode.get(0);
  }
}
