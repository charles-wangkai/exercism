import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimpleLinkedListTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void aNewListIsEmpty() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
		assertThat(list.size(), is(0));
	}

	@Test
	public void canCreateFromArray() {
		Character[] values = new Character[] { '1', '2', '3' };
		SimpleLinkedList<Character> list = new SimpleLinkedList<Character>(values);
		assertThat(list.size(), is(3));
	}

	@Test
	public void popOnEmptyListWillThrow() {
		expectedException.expect(NoSuchElementException.class);
		SimpleLinkedList<String> list = new SimpleLinkedList<String>();
		list.pop();
	}

	@Test
	public void popReturnsLastAddedElement() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
		list.push(9);
		list.push(8);
		assertThat(list.size(), is(2));
		assertThat(list.pop(), is(8));
		assertThat(list.pop(), is(9));
		assertThat(list.size(), is(0));
	}

	@Test
	public void reverseReversesList() {
		SimpleLinkedList<String> list = new SimpleLinkedList<String>();
		list.push("9");
		list.push("8");
		list.push("7");
		list.push("6");
		list.push("5");
		list.reverse();
		assertThat(list.pop(), is("9"));
		assertThat(list.pop(), is("8"));
		assertThat(list.pop(), is("7"));
		assertThat(list.pop(), is("6"));
		assertThat(list.pop(), is("5"));
	}

	@Test
	public void canReturnListAsArray() {
		SimpleLinkedList<Character> list = new SimpleLinkedList<Character>();
		list.push('9');
		list.push('8');
		list.push('7');
		list.push('6');
		list.push('5');
		Character[] expected = { '5', '6', '7', '8', '9' };
		assertArrayEquals(expected, list.asArray(Character.class));
	}

	@Test
	public void canReturnEmptyListAsEmptyArray() {
		SimpleLinkedList<Object> list = new SimpleLinkedList<Object>();
		Object[] expected = {};
		assertArrayEquals(expected, list.asArray(Object.class));
	}

}
