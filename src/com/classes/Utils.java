package com.classes;

public class Utils {
    public static boolean isCorrectSide(double side) {
        return side > 0;
    }

    public static String formatDouble(double num) {
        return String.format("%5.2f", num);
    }
}
