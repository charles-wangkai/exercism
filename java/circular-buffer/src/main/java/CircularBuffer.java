public class CircularBuffer<T> {
	T[] buffer;
	int headIndex;
	int tailIndex;
	int length;

	@SuppressWarnings("unchecked")
	CircularBuffer(int size) {
		buffer = (T[]) new Object[size];

		clear();
	}

	T read() throws BufferIOException {
		if (isEmpty()) {
			throw new BufferIOException("Tried to read from empty buffer");
		}

		T result = buffer[headIndex];
		headIndex = (headIndex + 1) % buffer.length;
		length--;
		return result;
	}

	void write(T element) throws BufferIOException {
		if (isFull()) {
			throw new BufferIOException("Tried to write to full buffer");
		}

		buffer[tailIndex] = element;
		tailIndex = (tailIndex + 1) % buffer.length;
		length++;
	}

	void overwrite(T element) throws BufferIOException {
		if (isFull()) {
			read();
		}
		write(element);
	}

	void clear() {
		headIndex = 0;
		tailIndex = 0;
		length = 0;
	}

	boolean isEmpty() {
		return length == 0;
	}

	boolean isFull() {
		return length > 0 && headIndex == tailIndex;
	}
}