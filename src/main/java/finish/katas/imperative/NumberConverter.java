package finish.katas.imperative;

import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.ImmutableMap;

public class NumberConverter {

    private static final Map<Integer, String> NUMBERS =
            new ImmutableMap.Builder<Integer, String>().put(1, "one")
                                                       .put(2, "two")
                                                       .put(3, "three")
                                                       .put(4, "four")
                                                       .put(5, "five")
                                                       .put(6, "six")
                                                       .put(7, "seven")
                                                       .put(8, "eight")
                                                       .put(9, "nine")
                                                       .put(10, "ten")
                                                       .put(11, "eleven")
                                                       .put(12, "twelve")
                                                       .put(13, "thirteen")
                                                       .put(14, "fourteen")
                                                       .put(15, "fifteen")
                                                       .put(16, "sixteen")
                                                       .put(17, "seventeen")
                                                       .put(18, "eighteen")
                                                       .put(19, "nineteen")
                                                       .put(20, "twenty")
                                                       .put(30, "thirty")
                                                       .put(40, "forty")
                                                       .put(50, "fifty")
                                                       .put(60, "sixty")
                                                       .put(70, "seventy")
                                                       .put(80, "eighty")
                                                       .put(90, "ninety")
                                                       .build();

    public static void main(String[] args) {
        String input = promptAndReadInput();
        while (!isExit(input)) {
            if (isValidNumber(input)) {
                print(inEnglish(integerValueOf(input)));
            }
            input = promptAndReadInput();
        }
    }

    private static int integerValueOf(String input) {
        return Integer.parseInt(input);
    }

    private static boolean isValidNumber(String input) {
        try {
            int number = integerValueOf(input);
            return (number > 0) && (number < 10000);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isExit(String input) {
        return "exit".equalsIgnoreCase(input);
    }

    @SuppressWarnings("resource")
    private static String promptAndReadInput() {
        System.out.print("Number > ");
        return new Scanner(System.in).next();
    }

    private static String inEnglish(int number) {
        return thousands(number) + hundreds(number) + and(number) + tens(number);
    }

    private static String thousands(int number) {
        // e.g. 1230 -> 1
        int thousands = number / 1000;
        if (thousands > 0) {
            return NUMBERS.get(thousands) + " thousand ";
        }
        return "";
    }

    private static String hundreds(int number) {
        // e.g. 1230 -> 230 -> 2
        int hundreds = (number % 1000) / 100;
        if (hundreds > 0) {
            return NUMBERS.get(hundreds) + " hundred ";
        }
        return "";
    }

    private static String and(int number) {
        if ((number > 100) && ((number % 100) > 0)) {
            return "and ";
        }
        return "";
    }

    private static String tens(int number) {
        // e.g. 121 -> 21
        int remainder = number % 100;
        if (remainder > 20) {
            // e.g. 21 -> 2 -> 20
            int tens = (remainder / 10) * 10;
            return NUMBERS.get(tens) + units(number);
        }
        return (remainder > 0) ? NUMBERS.get(remainder) : "";
    }

    private static String units(int number) {
        int units = number % 10;
        if (units > 0) {
            return " " + NUMBERS.get(units);
        }
        return "";
    }

    private static void print(String words) {
        System.out.println(words);
    }
}
