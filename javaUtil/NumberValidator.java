package com.cl.clutils.validator;

import java.math.BigDecimal;

/**
 * Validate if string can be converted to number type
 */
public class NumberValidator {

    public static boolean isNumeric(String number) {
        try {
            new BigDecimal(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isInteger(String number) {
        try {
            new Integer(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isLong(String number) {
        try {
            new Long(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFloat(String number) {
        try {
            new Float(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDouble(String number) {
        try {
            new Double(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBigDecimal(String number) {
        return isNumeric(number);
    }

}
