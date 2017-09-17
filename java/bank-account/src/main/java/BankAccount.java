public class BankAccount {
	int balance;
	boolean enabled;

	void open() {
		enabled = true;
	}

	int getBalance() throws BankAccountActionInvalidException {
		checkEnabled();

		return balance;
	}

	synchronized void deposit(int amount) throws BankAccountActionInvalidException {
		checkEnabled();
		checkAmount(amount);

		balance += amount;
	}

	synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
		checkEnabled();
		checkAmount(amount);

		if (balance == 0) {
			throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
		}
		if (amount > balance) {
			throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
		}

		balance -= amount;
	}

	private void checkAmount(int amount) throws BankAccountActionInvalidException {
		if (amount < 0) {
			throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
		}
	}

	private void checkEnabled() throws BankAccountActionInvalidException {
		if (!enabled) {
			throw new BankAccountActionInvalidException("Account closed");
		}
	}

	void close() {
		enabled = false;
	}
}
