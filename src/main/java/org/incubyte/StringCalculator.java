package org.incubyte;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String number) {
        if (number.isEmpty()) {
            return 0;
        }

        if (number.startsWith("//")) {
            return handleCustomDelimiter(number);
        }

        return sumNumbers(number.split("[,\\n]"));
    }

    private int handleCustomDelimiter(String number) {

        String delimiterLine = number.split("\n")[0];
        String numbers = number.substring(delimiterLine.length() + 1);

        String delimiter = extractDelimiter(delimiterLine);

        return sumNumbers(numbers.split(Pattern.quote(delimiter)));
    }

    private String extractDelimiter(String delimiterLine) {

            return delimiterLine.substring(2);

    }

    private int sumNumbers(String[] numbers) {
        try {
            return (int) Arrays.stream(numbers)
                    .mapToDouble(Double::parseDouble)
                    .sum();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non numerical value");
        }
    }
}
