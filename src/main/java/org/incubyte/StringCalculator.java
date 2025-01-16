package org.incubyte;

import java.util.Arrays;

public class StringCalculator {

    public int add(String number){

        if(number.isEmpty()){
            return 0;
        }

        String[] numbers = number.split(",");
        try {
            return (int) Arrays.stream(numbers).mapToDouble(Double::parseDouble).sum();
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Non numerical value");
        }

    }
}

