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

    public Str() {
    }

    public boolean check_int(String line) {
        try {
            long number_int = Long.parseLong(line);
            if (count_int == 0) {
                min_int = number_int;
                max_int = number_int;
            } else if (number_int > max_int) {
                max_int = number_int;
            } else if (number_int < min_int) {
                min_int = number_int;
            }

            ++count_int;
            sum_int += number_int;
            return true;
        } catch (NumberFormatException var4) {
            return false;
        }
    }

    public boolean check_double(String line) {
        try {
            double number_float = Double.parseDouble(line);
            if (count_float == 0) {
                min_float = number_float;
                max_float = number_float;
            } else if (number_float > max_float) {
                max_float = number_float;
            } else if (number_float < min_float) {
                min_float = number_float;
            }

            ++count_float;
            sum_float += number_float;
            return true;
        } catch (NumberFormatException var4) {
            return false;
        }
    }

    public boolean check_string(String line) {
        if (!line.matches(".*\\d+.*")) {
            if (count_string == 0) {
                str_max = line.length();
                str_min = line.length();
            } else if (line.length() > str_max) {
                str_max = line.length();
            } else if (line.length() < str_min) {
                str_min = line.length();
            }

            ++count_string;
            return true;
        } else {
            return false;
        }
    }

    public static int get_countInt() {
        return count_int;
    }

    public static int get_countDouble() {
        return count_float;
    }

    public static int get_countString() {
        return count_string;
    }

    public static void statistics(Flags flags, String result_int, String result_float, String result_string) {
        if (flags.s || flags.f) {
            System.out.println("STATISTICS:\n\n" + result_int + "\nNumber of elements:" + count_int + "\n");
            System.out.println(result_float + "\nNumber of elements:" + count_float + "\n");
            System.out.println(result_string + "\nNumber of elements:" + count_string + "\n");
        }
        if (flags.f) {
            if (count_int != 0) {
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


