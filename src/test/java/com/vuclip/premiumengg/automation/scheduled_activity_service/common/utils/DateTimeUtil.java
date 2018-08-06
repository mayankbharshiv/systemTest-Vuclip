package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class DateTimeUtil {

    public static BigInteger getDateByAddingValidity(BigInteger dateInMillis, int validity, String timeUnit) {
        TimeUnitEnum timeUnitEnum = TimeUnitEnum.valueOf(TimeUnitEnum.class, timeUnit);
        long validityInMillis = timeUnitEnum.toSeconds() * validity * 1000;
        return dateInMillis.add(BigInteger.valueOf(validityInMillis));
    }

    public static BigInteger getDateBySubtractingValidity(BigInteger dateInMillis, int validity, String timeUnit) {
        TimeUnitEnum timeUnitEnum = TimeUnitEnum.valueOf(TimeUnitEnum.class, timeUnit);
        long validityInMillis = timeUnitEnum.toSeconds() * validity * 1000;
        return dateInMillis.subtract(BigInteger.valueOf(validityInMillis));
    }

    public static long getDateByAddingValidity(long dateInMillis, int validity, String timeUnit) {
        TimeUnitEnum timeUnitEnum = TimeUnitEnum.valueOf(TimeUnitEnum.class, timeUnit);
        long validityInMillis = timeUnitEnum.toSeconds() * validity * 1000;
        return validityInMillis;
    }

    public static long getDateBySubtractingValidity(long dateInMillis, int validity, String timeUnit) {
        TimeUnitEnum timeUnitEnum = TimeUnitEnum.valueOf(TimeUnitEnum.class, timeUnit);
        long validityInMillis = timeUnitEnum.toSeconds() * validity * 1000;
        return validityInMillis;
    }

    public static BigInteger getCurrentDateInGMT() {
        return BigInteger.valueOf(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis());
    }

    public static long getCurrentDate() {
        return (Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis());
    }
    public static long getTimeInMillis(String dd, String mm, String yyyy, String HH, String MM, String SS) {

        LocalDateTime localDateTime = LocalDateTime.parse(yyyy + "/" + mm + "/" + dd + " " + HH + ":" + MM + ":" + SS,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    }

    public static long getTimeInMillisAddDays(int days) {
        LocalDateTime s = LocalDateTime.now().plusDays(days);
        return s.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    }

    public static long getTimeInMillisMinusDays(int days) {
        LocalDateTime s = LocalDateTime.now().minusDays(days);
        return s.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    }

}
