import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ListOps {

	static <T> List<T> append(List<T> list1, List<T> list2) {
		return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
	}

	static <T> List<T> concat(List<List<T>> listOfLists) {
		return listOfLists.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
	}

	static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		return list.stream().filter(predicate).collect(Collectors.toList());
	}

	static <T> int size(List<T> list) {
		return (int) list.stream().count();
	}

	static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
		return list.stream().map(transform).collect(Collectors.toList());
	}

	static <T> List<T> reverse(List<T> list) {
		return IntStream.range(0, list.size()).mapToObj(i -> list.get(list.size() - 1 - i))
				.collect(Collectors.toList());
	}

	static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
		U result = initial;
		for (T element : list) {
			result = f.apply(result, element);
		}
		return result;
	}

	static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
		U result = initial;
		for (T element : reverse(list)) {
			result = f.apply(element, result);
		}
		return result;
	}

	private ListOps() {
		// No instances.
	}

}
