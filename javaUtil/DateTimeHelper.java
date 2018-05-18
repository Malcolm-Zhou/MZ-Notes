package com.cl.clutils.helper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Util to handle JDK8 LocalDateTime related conversion
 */
public class DateTimeHelper {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    /**
     * @param dateTime standard format yyyy-MM-dd'T'HH:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(final String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }


    /**
     * @param date standard format yyyy-MM-dd'T'HH:mm:ss
     * @return LocalDate
     */
    public static LocalDate getLocalDate(final String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * @param dateTime     standard format yyyy-MM-dd'T'HH:mm:ss
     * @param targetFormat new format string
     * @return dateTime string
     */
    public static String getString(final String dateTime, final String targetFormat) {
        return getString(dateTime, DEFAULT_DATE_TIME_FORMAT, targetFormat);
    }


    /**
     * @param dateTime     time string in your format
     * @param sourceFormat your format string
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(final String dateTime, final String sourceFormat) {
        final DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern(sourceFormat);
        return LocalDateTime.parse(dateTime, newFormatter);
    }

    /**
     * @param dateTime     time string in your format
     * @param sourceFormat your format string
     * @return LocalDate
     */
    public static LocalDate getLocalDate(final String dateTime, final String sourceFormat) {
        final DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern(sourceFormat);
        return LocalDate.parse(dateTime, newFormatter);
    }

    /**
     * @param time         time string in your format
     * @param sourceFormat your format string
     * @return LocalTime
     */
    public static LocalTime getLocalTime(final String time, final String sourceFormat) {
        final DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern(sourceFormat);
        return LocalTime.parse(time, newFormatter);
    }


    /**
     * @param dateTime     time string in your format
     * @param sourceFormat old format string
     * @param targetFormat new format string
     * @return dateTime string
     */
    public static String getString(final String dateTime, final String sourceFormat, final String targetFormat) {
        final TemporalAccessor temporalAccessor = DateTimeFormatter.ofPattern(sourceFormat).parse(dateTime);
        final DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetFormat);
        if (temporalAccessor.isSupported(ChronoField.HOUR_OF_DAY)) {
            final LocalDateTime time = LocalDateTime.from(temporalAccessor);
            return time.format(targetFormatter);
        } else {
            final LocalDate date = LocalDate.from(temporalAccessor);
            final LocalTime time = LocalTime.of(0, 0, 0, 0);
            return LocalDateTime.of(date, time).format(targetFormatter);
        }
    }

    /**
     * Convert from java.util.Date to LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(final Date date) {
        final Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Convert from java.util.Date to LocalDate
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate getLocalDate(final Date date) {
        final Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Convert from LocalDateTime to java.util.Date
     *
     * @param dateTime
     * @return Date
     */
    public static Date localDateTimeToDate(final LocalDateTime dateTime) {
        final Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * Convert from java.util.Date to String
     *
     * @param date
     * @param format
     * @return String
     */
    public static String dateToString(final Date date, final String format) {
        final LocalDateTime localDateTime = getLocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * check data1 is before than the data2
     *
     * @param data1
     * @param data2
     * @return boolean
     */
    public static boolean isBefore(final Date data1, final Date data2) {
        final LocalDateTime localeDataTime1 = DateTimeHelper.getLocalDateTime(data1);
        final LocalDateTime localeDataTime2 = DateTimeHelper.getLocalDateTime(data2);

        return localeDataTime1.isBefore(localeDataTime2);
    }

    public static Date setTimeZeroinDate(final Date date) {
        LocalDateTime localDateTime = getLocalDateTime(date).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return localDateTimeToDate(localDateTime);
    }

}