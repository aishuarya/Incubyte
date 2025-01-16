package org.incubyte;

import java.util.Arrays;

public class StringCalculator {

    public int add(String number){

        if(number.isEmpty()){
            return 0;
        }

        String[] numbers = number.split(",");

        return  (int) Arrays.stream(numbers).mapToDouble(Double::parseDouble).sum();

    }
}

