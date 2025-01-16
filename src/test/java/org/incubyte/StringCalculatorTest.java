package org.incubyte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StringCalculatorTest {

    private StringCalculator underTest = new StringCalculator();

    @Test
    public void empty_String_should_return_zero(){
        int actual = underTest.add("");
        Assertions.assertEquals(0,actual);
    }

    @Test
    public void single_number_returns_same_number(){
        int actual = underTest.add("1");
        Assertions.assertEquals(1,actual);
    }

    @Test
    public void two_numbers_returns_sum(){
        int actual = underTest.add("1,1");
        Assertions.assertEquals(2,actual);
    }


    @ParameterizedTest
    @MethodSource("invalidInputs")
    public void invalid_input_should_throw_exception(String invalidInputs) {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            underTest.add(invalidInputs);
        });

        Assertions.assertEquals("Non numerical value", thrown.getMessage());

    }

    @ParameterizedTest
    @MethodSource("multipleNumbers")
    public void multiple_numbers_returns_sum(NumberSumTestData numberSumTestData){
        int actual = underTest.add(numberSumTestData.input);
        Assertions.assertEquals(numberSumTestData.output,actual);
    }

    @ParameterizedTest
    @MethodSource("multipleNumbersLineFeedSeparated")
    public void multiple_numbers_line_feed_separated_returns_sum(NumberSumTestData numberSumTestData){
        int actual = underTest.add(numberSumTestData.input);
        Assertions.assertEquals(numberSumTestData.output,actual);
    }

    @ParameterizedTest
    @MethodSource("customDelimeters")
    public void custom_delimiter_should_return_sum(NumberSumTestData customDelimeters) {
        int result = underTest.add(customDelimeters.input);
        Assertions.assertEquals(customDelimeters.output, result);
    }

    @ParameterizedTest
    @MethodSource("invalidInputsForCustomDelimeters")
    public void invalid_input_for_custom_delimeter_should_throw_exception(String invalidInputs) {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            underTest.add(invalidInputs);
        });

        Assertions.assertEquals("Non numerical value", thrown.getMessage());

    }
    private static Stream<NumberSumTestData> multipleNumbers(){
        return  Stream.of(
                new NumberSumTestData("1", 1),
                new NumberSumTestData("1,2", 3),
                new NumberSumTestData("1.1,2.2,3.3,4.4", 11),
                new NumberSumTestData("1.1,2.2,3.3", 6)
        );
    }


    private static Stream<String> invalidInputs(){
        return Stream.of(
              "1,2,abc",
                "1,2,,3",
                "1,%,*,l"
        );
    }

    private static Stream<NumberSumTestData> multipleNumbersLineFeedSeparated(){
        return  Stream.of(
                new NumberSumTestData("//;\n1;2", 3),             // Custom delimiter ";" (valid)
                new NumberSumTestData("//;\n1;2;3", 6),           // Custom delimiter ";" (valid)
                new NumberSumTestData("//*\n1*2", 3),             // Custom delimiter "*" (valid)
                new NumberSumTestData("1,2", 3)                 // Default delimiters (comma and newline)

        );
    }

    private static Stream<NumberSumTestData> customDelimeters(){
        return  Stream.of(
                new NumberSumTestData("1\n2",3),
                new NumberSumTestData("1\n2\n3",6),
                new NumberSumTestData("1\n2,3",6)

        );
    }

    private static Stream<String> invalidInputsForCustomDelimeters(){
        return  Stream.of(
                "//;\n1,,2",     // Invalid, empty value between commas
                "//\\n\n1,2;3",  // Invalid, malformed delimiter `\\n` at start
                "//,,\n1,2;3",   // Invalid, double comma as delimiter (ambiguous case)
                "////;\n1;2",    // Invalid, multiple slashes in delimiter
                "//\\n\n1,2;3"   // Invalid, malformed delimiter `\\n`
        );
    }
}

class NumberSumTestData{
    String input;
    int output;

    public NumberSumTestData(String input, int output) {
        this.input = input;
        this.output = output;
    }
}