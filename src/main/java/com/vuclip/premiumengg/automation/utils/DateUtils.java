package com.vuclip.premiumengg.automation.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Create by Ashish_Chhabra
 */
public class DateUtils {

    private DateTimeFormatter formatterGMT;

    private DateTimeFormatter formatterLocalTime;

    private DateTimeFormatter formatterWithTimeZone;

    private DateTimeFormatter formatterDate;

    private String timeZone;

    /**
     * @param localTimeZone
     */
    public DateUtils(String localTimeZone) {

        this.timeZone = localTimeZone;
        formatterGMT = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss 'GMT'").withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));
        formatterLocalTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s").withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
        formatterWithTimeZone = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s Z").withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));
        formatterDate = DateTimeFormat.forPattern("yyyy-MM-dd ").withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    }

    /**
     * @return
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return
     */
    public String getCurrentGMTTime() {
        DateTime gmtTime = new DateTime(DateTimeZone.UTC);
        return formatterGMT.print(gmtTime);
    }

    /**
     * @param timeFormat
     * @param timeOffsetInHours
     * @return
     */
    public String getCurrentGMTTime(String timeFormat, int timeOffsetInHours) {
        DateTimeFormatter df = DateTimeFormat.forPattern(timeFormat);
        DateTime gmtTime = new DateTime(DateTimeZone.UTC);
        if (timeOffsetInHours > 0) {
            gmtTime = gmtTime.plusHours(timeOffsetInHours);
        } else {
            gmtTime = gmtTime.minusHours(-timeOffsetInHours);
        }
        return df.print(gmtTime);
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
        formatterGMT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT")));

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
     * @param windowInLocal1
     * @param windowInLocal2
     * @param timeToCheckinLocal
     * @param type
     * @return
     * @throws ParseException
     */
    public String getGMTTimeFromLocalWindow(String windowInLocal1, String windowInLocal2,
                                            String timeToCheckinLocal, String type) throws ParseException {

        String resultTime = null;

        String time1 = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))) + windowInLocal1;
        String time2 = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))) + windowInLocal2;

        Interval interval1 = new Interval(formatterLocalTime.parseDateTime(time1),
                formatterLocalTime.parseDateTime(time2));

        boolean interval1ContainsDateTime = interval1.contains(formatterLocalTime.parseDateTime(timeToCheckinLocal));

        if (formatterLocalTime.parseDateTime(timeToCheckinLocal).isBefore((formatterLocalTime.parseDateTime(time1)))) {
            resultTime = time1;

        } else if (interval1ContainsDateTime) {
            resultTime = time2;

        } else if ("success".equalsIgnoreCase(type)) {
            resultTime = formatterDate.print(formatterLocalTime.parseDateTime(timeToCheckinLocal)) + windowInLocal1;
        } else {
            resultTime = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).plusDays(1)) + windowInLocal1;

        }

        return getGMTTimefromLocal(resultTime);

    }

    /**
     * @param windowInLocal1
     * @param windowInLocal2
     * @param windowInLocal3
     * @param timeToCheckinLocal
     * @param type
     * @return
     * @throws ParseException
     */
    public String getGMTTimeFromLocalWindow(String windowInLocal1, String windowInLocal2, String windowInLocal3,
                                            String timeToCheckinLocal, String type) throws ParseException {
        String resultTime = null;

        String time1 = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))) + windowInLocal1;
        String time2 = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))) + windowInLocal2;
        String time3 = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))) + windowInLocal3;

        Interval interval1 = new Interval(formatterLocalTime.parseDateTime(time1),
                formatterLocalTime.parseDateTime(time2));
        Interval interval2 = new Interval(formatterLocalTime.parseDateTime(time2),
                formatterLocalTime.parseDateTime(time3));


        boolean interval1ContainsDateTime = interval1.contains(formatterLocalTime.parseDateTime(timeToCheckinLocal));
        boolean interval2ContainsDateTime = interval2.contains(formatterLocalTime.parseDateTime(timeToCheckinLocal));


        if (formatterLocalTime.parseDateTime(timeToCheckinLocal).isBefore((formatterLocalTime.parseDateTime(time1)))) {
            resultTime = time1;

        } else if (interval1ContainsDateTime) {
            resultTime = time2;

        } else if (interval2ContainsDateTime) {
            resultTime = time3;

        } else if ("success".equalsIgnoreCase(type)) {
            resultTime = formatterDate.print(formatterLocalTime.parseDateTime(timeToCheckinLocal)) + windowInLocal1;
        } else {
            resultTime = formatterDate.print(new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).plusDays(1)) + windowInLocal1;
        }

        return getGMTTimefromLocal(resultTime);
    }

    /**
     * @param date1
     * @param date2
     * @param thresholdInMinutes
     * @return
     * @throws ParseException
     */
    public boolean compareDates(String date1, String date2, int thresholdInMinutes) throws ParseException {

        long diff = Math.abs(formatterGMT.parseDateTime(date1).getMillis() - formatterGMT.parseDateTime(date2).getMillis());

        if (diff == 0) {
            return true;
        } else if (diff != 0 && (diff / (60 * 1000) % 60) <= thresholdInMinutes) {
            return true;
        } else {
            return false;
        }
    }
}
