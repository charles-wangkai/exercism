import secrets
import string


class Cipher(object):
    def __init__(self, key=None):
        self.key = ''.join([secrets.choice(string.ascii_lowercase)
                            for _ in range(100)]) if key is None else key

        if not all(map(str.islower, self.key)):
            raise ValueError

    def encode(self, text):
        text = ''.join(filter(str.islower, text.lower()))
        return ''.join([chr((ord(text[i]) - ord('a') + ord(self.key[i % len(self.key)]) - ord('a')) % 26 + ord('a')) for i in range(len(text))])

    def decode(self, text):
        return ''.join([chr((ord(text[i]) - ord(self.key[i % len(self.key)]) + 26) % 26 + ord('a')) for i in range(len(text))])


class Caesar(Cipher):
    def __init__(self):
        super().__init__('d')
