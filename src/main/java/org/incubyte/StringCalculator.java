package org.incubyte;

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
        StringBuilder negativeNumbers = new StringBuilder();
        double sum = 0;

        for (String num : numbers) {
            try {
                double number = Double.parseDouble(num);
                if (number < 0) {
                    if (!negativeNumbers.isEmpty()) {
                        negativeNumbers.append(", ");
                    }
                    negativeNumbers.append(num);
                }
                sum += number;

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Non numerical value");
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negativeNumbers);
        }

        return (int) sum;
    }
}
