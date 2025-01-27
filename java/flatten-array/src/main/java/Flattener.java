import java.util.ArrayList;
import java.util.List;

public class Flattener {
  List<Object> flatten(List<Object> list) {
    List<Object> values = new ArrayList<>();
    search(values, list);

    return values;
  }

  @SuppressWarnings("unchecked")
  void search(List<Object> values, List<Object> list) {
    for (Object element : list) {
      if (element != null) {
        if (element instanceof List l) {
          search(values, l);
        } else {
          values.add(element);
        }
      }
    }
  }
}
