import secrets


def private_key(p):
    return secrets.randbelow(p - 2) + 2


def public_key(p, g, private):
    return pow(g, private, p)


def secret(p, public, private):
    return pow(public, private, p)
