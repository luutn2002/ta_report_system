package jp.ac.u_aizu.ta_report_system.utils;

import java.time.LocalTime;

public class TimeUtils {
  
  private TimeUtils() {}

  public static LocalTime convertMinuteToLocalTime(Integer minute) {
    return LocalTime.of((int) Math.floor(minute / 60), minute % 60);
  }

  public static Integer convertLocalTimeToMinute(LocalTime localTime) {
    return localTime.getHour() * 60 + localTime.getMinute();
  }

  public static Double convertMinuteToHour(Integer minute) {
    return Math.floor(minute / (double) 60 * 10) / 10;
  }
  
  public static Integer convertClockTimeStringToMinute(String clockTime) {
    String[] clockHourMinute = clockTime.split(":");
    return Integer.valueOf(clockHourMinute[0])*60 + Integer.valueOf(clockHourMinute[1]);
  }
}
