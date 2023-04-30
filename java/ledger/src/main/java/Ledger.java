import static java.util.Map.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Ledger {
  private static final Map<String, Locale> STR_TO_LOCALE =
      Map.ofEntries(entry("en-US", new EnUSLocale()), entry("nl-NL", new NlNLLocale()));
  private static final Map<String, String> CURRENCY_TO_SYMBOL =
      Map.ofEntries(entry("USD", "$"), entry("EUR", "€"));

  public LedgerEntry createLedgerEntry(String dateStr, String description, double change) {
    return new LedgerEntry(LocalDate.parse(dateStr), description, change);
  }

  public String format(String currency, String localeStr, LedgerEntry[] entries) {
    if (!STR_TO_LOCALE.containsKey(localeStr)) {
      throw new IllegalArgumentException("Invalid locale");
    }
    if (!CURRENCY_TO_SYMBOL.containsKey(currency)) {
      throw new IllegalArgumentException("Invalid currency");
    }

    Locale locale = STR_TO_LOCALE.get(localeStr);

    return generateHeaderRow(locale)
        + Arrays.stream(entries)
            .sorted(
                Comparator.comparing(LedgerEntry::date)
                    .thenComparing(LedgerEntry::change)
                    .thenComparing(LedgerEntry::description))
            .map(entry -> "\n" + generateEntryRow(entry, locale, currency))
            .collect(Collectors.joining());
  }

  private String generateHeaderRow(Locale locale) {
    return String.format(
        "%-10s | %-25s | %-13s",
        locale.headerDateStr, locale.headerDescriptionStr, locale.headerChangeStr);
  }

  private String generateEntryRow(LedgerEntry entry, Locale locale, String currency) {
    return String.format(
        "%s | %-25s | %13s",
        locale.generateDateStr(entry.date),
        generateDescriptionStr(entry.description),
        locale.generateChangeStr(entry.change, CURRENCY_TO_SYMBOL.get(currency)));
  }

  private String generateDescriptionStr(String description) {
    return (description.length() <= 25) ? description : (description.substring(0, 22) + "...");
  }

  public static record LedgerEntry(LocalDate date, String description, double change) {}
}

abstract class Locale {
  String headerDateStr;
  String headerDescriptionStr;
  String headerChangeStr;
  char decimalPoint;
  char thousandsSeparator;

  Locale(
      String headerDateStr,
      String headerDescriptionStr,
      String headerChangeStr,
      char decimalPoint,
      char thousandsSeparator) {
    this.headerDateStr = headerDateStr;
    this.headerDescriptionStr = headerDescriptionStr;
    this.headerChangeStr = headerChangeStr;
    this.decimalPoint = decimalPoint;
    this.thousandsSeparator = thousandsSeparator;
  }

  abstract String generateDateStr(LocalDate date);

  String generateChangeStr(double change, String symbol) {
    return ((change < 0) ? "-" : "")
        + symbol
        + String.format("%,.2f", Math.abs(change))
            .chars()
            .mapToObj(
                c -> {
                  if (c == '.') {
                    return decimalPoint;
                  }
                  if (c == ',') {
                    return thousandsSeparator;
                  }

                  return (char) c;
                })
            .map(String::valueOf)
            .collect(Collectors.joining());
  }
}

class EnUSLocale extends Locale {
  EnUSLocale() {
    super("Date", "Description", "Change", '.', ',');
  }

  @Override
  String generateDateStr(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
  }
}

class NlNLLocale extends Locale {
  NlNLLocale() {
    super("Datum", "Omschrijving", "Verandering", ',', '.');
  }

  @Override
  String generateDateStr(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }
}
