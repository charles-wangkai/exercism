class CustomUncheckedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	CustomUncheckedException() {
	}

	CustomUncheckedException(String message) {
		super(message);
	}
}
