class CustomCheckedException extends Exception {
	private static final long serialVersionUID = 1L;

	CustomCheckedException() {
	}

	CustomCheckedException(String message) {
		super(message);
	}
}
