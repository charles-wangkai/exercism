import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class React {
  public static class Cell<T> {
    List<ComputeCell<T>> listeners = new ArrayList<>();

    public T getValue() {
      throw new UnsupportedOperationException("Please implement the Cell.getValue() method");
    }

    void search(Set<ComputeCell<T>> allListeners) {
      allListeners.addAll(listeners);

      for (ComputeCell<T> listener : listeners) {
        listener.search(allListeners);
      }
    }
  }

  public static class InputCell<T> extends Cell<T> {
    T value;

    public InputCell(T value) {
      this.value = value;
    }

    public void setValue(T newValue) {
      Set<ComputeCell<T>> allListeners = new HashSet<>();
      search(allListeners);

      Map<ComputeCell<T>, T> listenerToOldValue =
          allListeners.stream()
              .collect(Collectors.toMap(listener -> listener, ComputeCell::getValue));

      value = newValue;

      for (ComputeCell<T> listener : allListeners) {
        if (!listener.getValue().equals(listenerToOldValue.get(listener))) {
          for (Consumer<T> callback : listener.callbacks) {
            callback.accept(listener.getValue());
          }
        }
      }
    }

    @Override
    public T getValue() {
      return value;
    }
  }

  public static class ComputeCell<T> extends Cell<T> {
    Function<List<T>, T> function;
    List<Cell<T>> cells;
    List<Consumer<T>> callbacks = new ArrayList<>();

    public ComputeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
      for (Cell<T> cell : cells) {
        cell.listeners.add(this);
      }

      this.function = function;
      this.cells = cells;
    }

    public void addCallback(Consumer<T> callback) {
      callbacks.add(callback);
    }

    public void removeCallback(Consumer<T> callback) {
      callbacks.remove(callback);
    }

    @Override
    public T getValue() {
      return function.apply(cells.stream().map(Cell::getValue).toList());
    }
  }

  public static <T> InputCell<T> inputCell(T initialValue) {
    return new InputCell<>(initialValue);
  }

  public static <T> ComputeCell<T> computeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
    return new ComputeCell<>(function, cells);
  }
}
