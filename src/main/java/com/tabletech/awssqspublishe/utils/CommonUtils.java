package com.tabletech.awssqspublishe.utils;

import io.micrometer.common.util.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final Pattern WHOLE_NUMBER_PATTERN = Pattern.compile("[-+]?\\d+");

    public static Integer parseIntegerFromString(String numberString, Integer defaultValue) {
        return parseIntegerFromString(numberString).orElse(defaultValue);
    }

    public static Optional<Integer> parseIntegerFromString(String numberString) {
        if (StringUtils.isBlank(numberString)) {
            return Optional.empty();
        }
        Matcher matcher = WHOLE_NUMBER_PATTERN.matcher(numberString.trim());
        if (matcher.matches()) {
            return Optional.of(Integer.parseInt(numberString.trim()));
        } else {
            return Optional.empty();
        }
    }
}
