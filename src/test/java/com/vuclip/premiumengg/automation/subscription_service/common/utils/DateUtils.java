package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import com.vuclip.premiumengg.automation.utils.AppAssert;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/*
 * Created By rahul s
 */
public class DateUtils {

    public static DateTimeFormatter formatterGMT;
    private static DateTimeFormatter formatterWithTimeZone;
    private DateTimeFormatter formatterLocalTime;
    private String timeZone;

    public DateUtils(String localTimeZone) {

        this.timeZone = localTimeZone;
        formatterGMT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));
        formatterLocalTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s")
                .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
        formatterWithTimeZone = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s Z")
                .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));

    }

    /**
     * @param date1
     * @param date2
     * @param thresholdInMinutes
     * @return
     */
    public static boolean compareDates(String date1, String date2, int thresholdInDays) throws ParseException {

        long diff = Math
                .abs(formatterGMT.parseDateTime(date1).getMillis() - formatterGMT.parseDateTime(date2).getMillis());

        if (diff == 0) {
            return true;
        } else if (diff != 0 && (diff / (60 * 1000 * 60 * 24)) <= thresholdInDays) {
            return true;
        } else {
            return false;
        }
    }

    public static String convertToString(Date date) {

        Instant di1 = Instant.ofEpochMilli(date.getTime());
        LocalDateTime l1 = LocalDateTime.ofInstant(di1, ZoneId.of("GMT"));
        System.out.println(l1.toString());
        return l1.toString();
    }

    public static String convertLongToStringDate(Long date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return (formatter.format(calendar.getTime()));
    }

    /**
     * Java date.toString() direct can be passed to this function for which format
     * is EEE MMM dd HH:mm:ss zzz yyyy
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static long getDaysBetweenJavaDates(String date1, String date2) {
        System.out.println(date1 + "   " + date2);
        return getDaysBetweenDates(date1, date2, TimeFormatter.javaDateFormatter);
    }

    public static long getDaysBetweenDates(String date1, String date2, SimpleDateFormat dateformatter) {
        long diff = 0;
        try {
            diff = Math.abs(dateformatter.parse(date1).getTime() - dateformatter.parse(date2).getTime());
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
            AppAssert.assertTrue(false, "Make sure format is correct");
        }

        return (diff / (60 * 1000 * 60 * 24));
    }

    public static String extractDateTimeFromGMTTime(String dateStr, DateFormat dateFormat) {
        try {
            return convertToString(dateFormat.parse(dateStr)).replaceAll("T", " ");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate.getTime();
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
        return 0;
    }

    public static Boolean convertIntToBoolean(int value) {
        Boolean myboolean = (value != 0);
        return myboolean;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @param time
     * @return
     * @throws ParseException
     */
    public String getGMTTimefromLocal(String time) throws ParseException {
        String tz = timeZone.replaceAll("GMT", "").replaceAll(":", "");
        return formatterWithTimeZone.parseMutableDateTime(time + " " + tz).toString(formatterGMT);
    }

    /**
     * @return
     * @throws ParseException
     */
    public Map<String, String> getGMTTimeforTpay() throws ParseException {

        DateTime gmtTime = new DateTime(DateTimeZone.UTC);

        formatterGMT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));

        Map<String, String> dateMap = new HashMap<String, String>();

        dateMap.put("paymentDate", formatterGMT.print(gmtTime).concat("Z"));
        dateMap.put("nextPaymentDate", formatterGMT.print(gmtTime.plusDays(1)).concat("Z"));

        return dateMap;
    }

    /**
     * @return
     */
    public String getCurrentLocalTime() {

        DateTime localTime = new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
        return formatterLocalTime.print(localTime);

    }

    /**
     * @param period
     * @param timeUnit
     * @return
     * @throws ParseException
     */
    public String addValidityGMT(String period, String timeUnit) throws ParseException {

        String resultTime = null;
        DateTime gmtTime = new DateTime(DateTimeZone.UTC);

        if ("DAY".equalsIgnoreCase(timeUnit)) {

            resultTime = formatterGMT.print((gmtTime.plusDays(Integer.parseInt(period))));

        } else if ("HOUR".equalsIgnoreCase(timeUnit)) {

            resultTime = formatterGMT.print((gmtTime.plusHours(Integer.parseInt(period))));
        }

        return resultTime;
    }

    /**
     * @param period
     * @param timeUnit
     * @return
     * @throws ParseException
     */
    public String addValidityLocal(String period, String timeUnit) throws ParseException {

        String resultTime = null;
        DateTime localTime = new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));

        if ("DAY".equalsIgnoreCase(timeUnit)) {

            resultTime = formatterLocalTime.print((localTime.plusDays(Integer.parseInt(period))));

        } else if ("HOUR".equalsIgnoreCase(timeUnit)) {

            resultTime = formatterLocalTime.print((localTime.plusHours(Integer.parseInt(period))));
        }

        return resultTime;
    }

    /**
     * @param date1 yyyy-MM-dd HH:mm:ss
     * @param date2 yyyy-MM-dd HH:mm:ss
     * @return
     */

    public Long addDays(String date1, int days) throws ParseException {

        long diff = (formatterGMT.parseDateTime(date1).getMillis());
        if (days == 0)
            return diff;

        return (((diff / (1000 * 60 * 60 * 24)) + days) * (60 * 1000 * 60 * 24));

    }

    public long convertToLong(Date date1, String zone) {

        Instant di1 = Instant.ofEpochMilli(date1.getTime());
        LocalDate l1 = LocalDateTime.ofInstant(di1, ZoneId.of(zone)).toLocalDate();
        return l1.toEpochDay();
    }

    /**
     * Java date directly passed
     *
     * @param date1
     * @param date2
     * @param formatter
     * @return
     * @throws ParseException
     */
    public long getDaysBetweenDates(Date date1, Date date2, SimpleDateFormat formatter) throws ParseException {
        return getDaysBetweenJavaDates(date1.toString(), date2.toString());
    }

}
