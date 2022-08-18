package com.mamoris.portfolio.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Federico Mamoris
 */
public class Validate {

    public static boolean validateEmail(String input) {
        boolean status = false;
        String REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public static boolean validateIcon(String input) {
        boolean status = false;
        String REGEX = "fa-[a-zA-Z0-9-]+";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public static boolean validateURL(String input) {
        boolean status = false;
        String REGEX = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]"
                + "+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+"
                + "[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))"
                + "[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }
}
