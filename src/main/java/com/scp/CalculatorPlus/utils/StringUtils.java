package com.scp.CalculatorPlus.utils;

import org.apache.commons.math3.fraction.BigFraction;

import java.math.RoundingMode;

public class StringUtils {

    public static String pluralizeWord(String word) {
        if (word.endsWith("y")) {
            word.substring(0, word.length() - 1).concat("ie");
        }
        return word.concat("s");
    }

    public static String bigFractionToString(BigFraction number) {
        return number.toString().contains("/")
                ? number.bigDecimalValue(2, RoundingMode.UP.ordinal()).toString()
                : number.toString();
    }
}
