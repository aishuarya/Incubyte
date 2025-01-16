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
    @MethodSource("multipleNumbers")
    public void multiple_numbers_returns_sum(NumberSumTestData numberSumTestData){
        int actual = underTest.add(numberSumTestData.input);
        Assertions.assertEquals(numberSumTestData.output,actual);
    }

    private static Stream<NumberSumTestData> multipleNumbers(){
        return  Stream.of(
                new NumberSumTestData("1", 1),
                new NumberSumTestData("1,2", 3),
                new NumberSumTestData("1.1,2.2,3.3,4.4", 11),
                new NumberSumTestData("1.1,2.2,3.3", 6)
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