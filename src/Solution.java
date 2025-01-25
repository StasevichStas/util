import java.io.*;
import java.util.ArrayList;

public class Solution {
    private static String result_int = "integers.txt";
    private static String result_floats = "floats.txt";
    private static String result_string = "strings.txt";

    public Solution() {
    }

    public static void main(String[] args) {
        Flags flags = new Flags(false, false, false, false, false);
        parsing(flags, args);
    }

    private static void parsing(Flags flags, String[] args) {
        String way = null;
        ArrayList<String> files_read = new ArrayList<>();

        for (int i = 0; i < args.length; ++i) {
            if (args[i].contains(".txt")) {
                files_read.add(args[i]);
            } else {
                switch (args[i]) {
                    case "-o":
                        flags.o = true;
                        ++i;
                        way = args[i];
                        break;
                    case "-p":
                        flags.p = true;
                        ++i;
                        result_int = args[i].concat(result_int);
                        result_floats = args[i].concat(result_floats);
                        result_string = args[i].concat(result_string);
                        break;
                    case "-s":
                        flags.s = true;
                        break;
                    case "-f":
                        flags.f = true;
                        break;
                    case "-a":
                        flags.a = true;
                        break;
                    default:
                        System.out.println("There is no flag [" + args[i] + "]");
                }
            }
        }

        if (way != null) {
            result_int = way.concat(result_int);
            result_floats = way.concat(result_floats);
            result_string = way.concat(result_string);
        }

        reading_file(flags, files_read);
        if (flags.f || flags.s) {
            Str.statistics(flags, result_int, result_floats, result_string);
        }
    }

    private static void reading_file(Flags flags, ArrayList<String> files_read) {
        BufferedWriter writer_int = null;
        BufferedWriter writer_float = null;
        BufferedWriter writer_string = null;
        ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();
        boolean all_files_finish = false;

        for (String arg : files_read) {
            try {
                bufferedReaders.add(new BufferedReader(new FileReader(arg)));
            } catch (IOException var11) {
                System.out.println("error: the file[" + arg + "] was not found for reading");
            }
        }

        try {
            while (!all_files_finish) {
                all_files_finish = true;

                for (BufferedReader reader : bufferedReaders) {
                    Str str = new Str();
                    String line;
                    if ((line = reader.readLine()) != null) {
                        all_files_finish = false;
                        if (str.check_int(line)) {
                            if (Str.get_countInt() == 1) {
                                writer_int = open_writer(flags, result_int);
                            }
                            if (writer_int != null) {
                                writer_int.write(line + "\n");
                            }
                        } else if (str.check_double(line)) {
                            if (Str.get_countDouble() == 1) {
                                writer_float = open_writer(flags, result_floats);
                            }
                            if (writer_float != null) {
                                writer_float.write(line + "\n");
                            }
                        } else if (str.check_string(line)) {
                            if (Str.get_countString() == 1) {
                                writer_string = open_writer(flags, result_string);
                            }

                            if (writer_string != null) {
                                writer_string.write(line + "\n");
                            }
                        } else {
                            System.out.println("error: the string contains numbers and symbols");
                        }
                    }
                }
            }
        } catch (IOException var12) {
            System.out.println("Error reading the file");
        }
        close_writer(writer_int);
        close_writer(writer_float);
        close_writer(writer_string);
    }

    private static BufferedWriter open_writer(Flags flags, String result_file) {
        if (flags.a) {
            try {
                return new BufferedWriter(new FileWriter(result_file, true));
            } catch (IOException var3) {
                throw new RuntimeException("error: when opening the results file[" + result_file + "] for additional recording");
            }
        } else {
            try {
                return new BufferedWriter(new FileWriter(result_file));
            } catch (IOException var4) {
                throw new RuntimeException("error: when opening the results file[" + result_file + "] for overwriting");
            }
        }
    }

    private static void close_writer(BufferedWriter writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException var2) {
                System.out.println("error:close file");
            }
        }
    }
}
