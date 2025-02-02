package com.example;

import lombok.Getter;
import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
@Getter
public class Str {

    private static int count_int = 0;
    private static int count_float = 0;
    private static int count_string = 0;
    private static long sum_int = 0L;
    private static long min_int;
    private static int str_min;
    private static long max_int;
    private static int str_max;
    private static double sum_float = (double) 0.0F;
    private static double min_float;
    private static double max_float;
    private static final String flagO = "o", flagP = "p", flagS = "s", flagF = "f", flagsA = "a";
    private final ArrayList<Double> arrayDouble = new ArrayList<>();
    private final ArrayList<Long> arrayInt = new ArrayList<>();
    private final ArrayList<String> arrayString = new ArrayList<>();

    public Str() {
    }

    public boolean checkInt(String line) {
        try {
            long numberInt = Long.parseLong(line);
            arrayInt.add(numberInt);
//            long number_int = Long.parseLong(line);
//            if (count_int == 0) {
//                min_int = number_int;
//                max_int = number_int;
//            } else if (number_int > max_int) {
//                max_int = number_int;
//            } else if (number_int < min_int) {
//                min_int = number_int;
//            }
//            ++count_int;
//            sum_int += number_int;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkFloat(String line) {
        try {
            double numberFloat = Double.parseDouble(line);
            arrayDouble.add(numberFloat);
//            double number_float = Double.parseDouble(line);
//            if (count_float == 0) {
//                min_float = number_float;
//                max_float = number_float;
//            } else if (number_float > max_float) {
//                max_float = number_float;
//            } else if (number_float < min_float) {
//                min_float = number_float;
//            }
//
//            ++count_float;
//            sum_float += number_float;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public  boolean checkString(String line) {
        if (!line.matches(".*\\d+.*")) {
            arrayString.add(line);
//            if (count_string == 0) {
//                str_max = line.length();
//                str_min = line.length();
//            } else if (line.length() > str_max) {
//                str_max = line.length();
//            } else if (line.length() < str_min) {
//                str_min = line.length();
//            }
//
//            ++count_string;
            return true;
        } else {
            return false;
        }
    }

    public  int getSizeInt() {
        return arrayInt.size();
    }

    public  int getSizeDouble() {
        return arrayDouble.size();
    }

    public  int getSizeString() {
        return arrayString.size();
    }

    public  void statistics(CommandLine commandLine, String result_int, String result_float, String result_string) {

        if (commandLine.hasOption(flagF) || commandLine.hasOption(flagF)) {
            System.out.println("STATISTICS:\n\n" + result_int + "\nNumber of elements:" + count_int + "\n");
            System.out.println(result_float + "\nNumber of elements:" + count_float + "\n");
            System.out.println(result_string + "\nNumber of elements:" + count_string + "\n");
        }
        if (commandLine.hasOption(flagF)) {
            if (count_int != 0) {
                long minInt = arrayInt.stream().min(Long::compare).orElse((long)0);
                long maxInt = arrayInt.stream().max(Long::compare).orElse((long)0);
                long sumInt = arrayInt.stream().mapToLong(Long::intValue).sum();

                System.out.println(result_int + "\nmin:" + min_int + "\nmax:" + max_int + "\nsum:" + sum_int + "\narithmetic mean:" + (double) (sum_int / (long) count_int) + "\n");
            }
            if (count_float != 0) {
                System.out.println(result_float + "\nmin:" + min_float + "\nmax:" + max_float + "\nsum:" + sum_float + "\narithmetic mean:" + sum_float / (double) count_float + "\n");
            }
            if (count_string != 0) {
                System.out.println(result_string + "\nmin:" + str_min + "\nmax:" + str_max);
            }
        }
    }

}


