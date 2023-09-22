package jp.ac.u_aizu.ta_report_system.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  private DateUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static final String MONTH_NAME = "MMMMM";

  public static final String DATE_SPLITTED_BY_SLASH = "yyyy/MM/dd";

  public static String formatDate(LocalDate targetDate, String strFormat) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(strFormat);
    return dateFormat.format(targetDate);
  }
}
