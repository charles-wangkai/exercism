import threading


class BankAccount(object):
    def __init__(self):
        self.lock = threading.Lock()
        self.balance = 0
        self.enabled = False

    def get_balance(self):
        self.check_enabled()

        return self.balance

    def open(self):
        if self.enabled:
            raise ValueError('Account is already opened!')

        self.enabled = True

    def deposit(self, amount):
        with self.lock:
            self.check_enabled()
            self.check_amount(amount)

            self.balance += amount

    def withdraw(self, amount):
        with self.lock:
            self.check_enabled()
            self.check_amount(amount)

            if amount > self.balance:
                raise ValueError('Amount can not exceed balance!')

            self.balance -= amount

    def close(self):
        if not self.enabled:
            raise ValueError('Account is already closed!')

        self.balance = 0
        self.enabled = False

    def check_enabled(self):
        if not self.enabled:
            raise ValueError('Account is closed!')

    def check_amount(self, amount):
        if amount < 0:
            raise ValueError('Amount can not be negative!')
