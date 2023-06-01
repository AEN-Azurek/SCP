package com.scp.CalculatorPlus.service.utils;

public class StringUtils {

    public static String pluralizeWord(String word) {
        if (word.endsWith("y")) {
            word.substring(0, word.length() - 1).concat("ie");
        }
        return word.concat("s");
    }
}
