import datetime


def add(birth_date):
    return birth_date + datetime.timedelta(seconds=1000000000)
