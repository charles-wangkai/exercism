import java.util.ArrayList;
import java.util.List;

public class Flattener {
	List<Object> flatten(List<Object> list) {
		List<Object> result = new ArrayList<Object>();
		search(result, list);
		return result;
	}

	@SuppressWarnings("unchecked")
	void search(List<Object> result, List<Object> list) {
		for (Object element : list) {
			if (element == null) {
				continue;
			}

			if (element instanceof List) {
				search(result, (List<Object>) element);

			} else {
				result.add(element);
			}
		}
	}
}
