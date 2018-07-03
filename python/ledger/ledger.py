from datetime import datetime

CURRENCY_TO_SYMBOL = {'USD': '$', 'EUR': '€'}


class LedgerEntry(object):
    def __init__(self, date, description, change):
        self.date = date
        self.description = description
        self.change = change


class Locale(object):
    @staticmethod
    def from_str(locale_str):
        if locale_str == 'en_US':
            return Locale_en_US()
        elif locale_str == 'nl_NL':
            return Locale_nl_NL()

    def generate_integer_str(self, integer):
        s = str(integer)

        return self.thousands_separator.join([s[max(0, i - 3):i] for i in range(len(s), 0, -3)][::-1])

    def generate_amount_str(self, change):
        amount = abs(change)

        return '{integer_str}{decimal_point}{fraction_str:02}'.format(
            integer_str=self.generate_integer_str(amount // 100),
            decimal_point=self.decimal_point,
            fraction_str=amount % 100)


class Locale_en_US(Locale):
    header_date_str = 'Date'
    header_description_str = 'Description'
    header_change_str = 'Change'

    decimal_point = '.'
    thousands_separator = ','

    def generate_date_str(self, date):
        return date.strftime('%m/%d/%Y')

    def generate_change_str(self, change, symbol):
        if change >= 0:
            format_str = '{symbol}{amount_str} '
        else:
            format_str = '({symbol}{amount_str})'

        return format_str.format(symbol=symbol, amount_str=self.generate_amount_str(change))


class Locale_nl_NL(Locale):
    header_date_str = 'Datum'
    header_description_str = 'Omschrijving'
    header_change_str = 'Verandering'

    decimal_point = ','
    thousands_separator = '.'

    def generate_date_str(self, date):
        return date.strftime('%d-%m-%Y')

    def generate_change_str(self, change, symbol):
        if change >= 0:
            format_str = '{symbol} {amount_str} '
        else:
            format_str = '{symbol} -{amount_str} '

        return format_str.format(symbol=symbol, amount_str=self.generate_amount_str(change))


def create_entry(date, description, change):
    return LedgerEntry(datetime.strptime(date, '%Y-%m-%d'), description, change)


def generate_header_row(locale):
    return '{header_date_str:<10} | {header_description_str:<25} | {header_change_str:<13}'.format(
        header_date_str=locale.header_date_str,
        header_description_str=locale.header_description_str,
        header_change_str=locale.header_change_str)


def generate_entry_row(entry, locale, currency):
    return '{date_str} | {description_str:<25} | {change_str:>13}'.format(
        date_str=locale.generate_date_str(entry.date),
        description_str=generate_description_str(entry.description),
        change_str=locale.generate_change_str(entry.change, CURRENCY_TO_SYMBOL[currency]))


def generate_description_str(description):
    return description if len(description) <= 25 else (description[:22] + '...')


def format_entries(currency, locale_str, entries):
    locale = Locale.from_str(locale_str)

    return '\n'.join([generate_header_row(locale)]
                     + [generate_entry_row(entry, locale, currency)
                        for entry in sorted(entries, key=lambda entry: (entry.date, entry.change, entry.description))])
