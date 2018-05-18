package com.cl.clutils.validator;

/**
 * Validate email address
 */
public class EmailValidator {

    public static boolean isValidated(String email) {
        if (email != null) {
            String regex = "^[.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            return email.matches(regex);
        } else {
            return false;
        }
    }

    public static boolean isValidated(String email, int maxLength) {
        if (email != null && email.length() <= maxLength) {
            String regex = "^[.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            return email.matches(regex);
        } else {
            return false;
        }
    }

}
