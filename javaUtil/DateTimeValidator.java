package com.cl.clutils.validator;

import com.cl.clutils.helper.DateTimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Validate date or time in string
 */
public class DateTimeValidator {
    private static final String DATE = "localDate";
    private static final String TIME = "localTime";
    private static final String DATE_TIME = "localDateTime";
    private static final String WRONG_TYPE = "wrongType";

    private static Logger LOG = LoggerFactory.getLogger(DateTimeValidator.class);

    //TODO Optimize getDateTimeType
    private static String getDateTimeType(String format) {
        if (format.contains("-") || format.contains("/")) {
            if (format.contains(":")) {
                return DATE_TIME;
            } else {
                return DATE;
            }
        } else {
            if (format.contains(":")) {
                return TIME;
            } else {
                return WRONG_TYPE;
            }
        }
    }

    public static boolean isValidated(String dateTimeString, String format) {
        String type = getDateTimeType(format);
        switch (type) {
            case DATE_TIME:
                return isValidatedDateTime(dateTimeString, format);
            case DATE:
                return isValidatedDate(dateTimeString, format);
            case TIME:
                return isValidatedTime(dateTimeString, format);
            case WRONG_TYPE:
                LOG.error("Date string doesn't contain '-', '/' or ':'");
        }
        return false;
    }

    public static boolean isValidatedDate(String dateString, String format) {
        try {
            DateTimeHelper.getLocalDate(dateString, format);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidatedTime(String timeString, String format) {
        try {
            DateTimeHelper.getLocalTime(timeString, format);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Parse Exception");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidatedDateTime(String dateTimeString, String format) {
        try {
            DateTimeHelper.getLocalDateTime(dateTimeString, format);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Parse Exception");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBefore(String early, String later, String format) {
        String type = getDateTimeType(format);
        switch (type) {
            case DATE_TIME:
                LocalDateTime earlyDateTime = DateTimeHelper.getLocalDateTime(early, format);
                LocalDateTime laterDateTime = DateTimeHelper.getLocalDateTime(later, format);
                return earlyDateTime.isBefore(laterDateTime);
            case DATE:
                LocalDate earlyDate = DateTimeHelper.getLocalDate(early, format);
                LocalDate laterDate = DateTimeHelper.getLocalDate(later, format);
                return earlyDate.isBefore(laterDate);
            case TIME:
                LocalTime earlyTime = DateTimeHelper.getLocalTime(early, format);
                LocalTime laterTime = DateTimeHelper.getLocalTime(later, format);
                return earlyTime.isBefore(laterTime);
            case WRONG_TYPE:
                LOG.error("Date string doesn't contain '-' or ':'");
        }
        return false;
    }
}
