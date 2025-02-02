package com.example;

import java.io.*;
//import org.slf4j.Logger;
//import org.slf4j.Logger;

//import java.util.logging.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

@Slf4j
//import org.slf4j.LoggerFactory;
public class Main {
    //private static final Logger logger = Logger.getLogger(Main.class);
    private static final String flagO = "o", flagP = "p", flagS = "s", flagF = "f", flagA = "a";
    private static String fileInt = "integers.txt";
    private static String fileFloat = "floats.txt";
    private static String fileString = "strings.txt";


    public static void main(String[] args) {
        if (args.length < 1) {
            log.error("Nothing came up on the command line .There must be at least one text file .");
            System.exit(2);
        }
        //Flags flags = new Flags();
        try {
            CommandLine commandLine = parsing(args);
            readingAndWritingFile(commandLine);
            if (commandLine.hasOption(flagF) || commandLine.hasOption(flagS))
                Str.statistics(commandLine, fileInt, fileFloat, fileString);
        } catch (Exception e) {
            throw new RuntimeException("program execution error " + e.getMessage());
        }

    }


    private static CommandLine parsing(String[] args) {
        Parser parser = new Parser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parseArguments(args);
            if (commandLine.hasOption(flagP)) {
                log.info("flag [-p]");
                fileInt = commandLine.getOptionValue(flagP).concat(fileInt);
                fileFloat = commandLine.getOptionValue(flagP).concat(fileFloat);
                fileString = commandLine.getOptionValue(flagP).concat(fileString);
            }
            if (commandLine.hasOption(flagO)) {
                log.info("flag[-o]");
                fileInt = commandLine.getOptionValue(flagO).concat(fileInt);
                fileFloat = commandLine.getOptionValue(flagO).concat(fileFloat);
                fileString = commandLine.getOptionValue(flagO).concat(fileString);
            }

        } catch (ParseException e) {
            log.warn("Ошибка при разборе аргументов: {}", e.getMessage());
        }
        return commandLine;
    }
//        String way = null;
//        ArrayList<String> files_read = new ArrayList<>();
//
//        for (int i = 0; i < args.length; ++i) {
//            if (args[i].contains(".txt")) {
//                files_read.add(args[i]);
//            } else {
//                switch (args[i]) {
//                    case flagO:
//                        flags.o = true;
//                        if (i + 1 < args.length) ++i;
//                        else System.out.println("Warning flags");
//                        way = args[i];
//                        break;
//                    case flagP:
//                        flags.p = true;
//                        if (i + 1 < args.length) ++i;
//                        else System.out.println("Warning flags");
//                        nameFileInt = args[i].concat(nameFileInt);
//                        nameFileFloat = args[i].concat(nameFileFloat);
//                        nameFileString = args[i].concat(nameFileString);
//                        break;
//                    case flagS:
//                        flags.s = true;
//                        break;
//                    case flagF:
//                        flags.f = true;
//                        break;
//                    case flagsA:
//                        flags.a = true;
//                        break;
//                    default:
//                        System.out.println("There is no flag [" + args[i] + "]");
//                }
//            }
//        }
//        if (way != null) {
//            nameFileInt = way.concat(nameFileInt);
//            nameFileFloat = way.concat(nameFileFloat);
//            nameFileString = way.concat(nameFileString);
//        }
    //return null;
    // }

    private static void readingAndWritingFile(CommandLine commandLine) {
        //ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();
        String[] arrayNameFiles = commandLine.getArgs();
        boolean allFilesFinished = false;
        Str str = new Str();
        long linesToSkip = 0;
        int numberFiles;
        // ArrayList<Integer> pointer = new ArrayList<>((Collections.nCopies(files_read.size(), 0)));

//        for (String arg : files_read) {
//            try {
//                bufferedReaders.add(new BufferedReader(new FileReader(arg)));
//            } catch (IOException e) {
//                logger.severe("error: the file[" + arg + "] was not found for reading");
//            }
//        }
        if (arrayNameFiles.length > 0) {
            while (!allFilesFinished) {
                allFilesFinished = true;
                //numberFiles = 0;
                for (String arg : arrayNameFiles) {
                    try (//RandomAccessFile raf = new RandomAccessFile(arg, "r");
                         //BufferedReader reader = new BufferedReader(new FileReader(raf.getFD()));
                         BufferedReader reader = new BufferedReader(new FileReader(arg));) {
                        for (int i = 0; i < linesToSkip; ++i) {
                            if (reader.readLine() == null) break;
                        }
                        //raf.seek(pointer.get(numberFiles));
                        String line = reader.readLine();
                        //System.out.println(raf.getFilePointer());
                        if (line == null) continue;
                        allFilesFinished = false;
                        if (!(str.checkInt(line) || str.checkFloat(line) || str.checkString(line)))
                            log.warn("Строка содержит цифры и символы,невозможно определить куда её отнести.");
                        //processLine(line, writerInt, writerFloat, writerString);
                        //pointer.set(numberFiles, (int) raf.getFilePointer());
                        //numberFiles++;
                    } catch (IOException e) {
                        throw new RuntimeException("Something");
                    }
                }
                linesToSkip++;
            }
            try (BufferedWriter writerInt = new BufferedWriter(new FileWriter(fileInt, commandLine.hasOption(flagA)));
                 BufferedWriter writerFloat = new BufferedWriter(new FileWriter(fileFloat, commandLine.hasOption(flagA)));
                 BufferedWriter writerString = new BufferedWriter(new FileWriter(fileString, commandLine.hasOption(flagA)))) {
                if (str.getSizeInt() != 0)
                    for (int i = 0; i < str.getSizeInt(); ++i)
                        writerInt.write(String.valueOf(str.getArrayInt().get(i)) + "\n");
                if (str.getSizeDouble() != 0)
                    for (int i = 0; i < str.getSizeDouble(); ++i)
                        writerFloat.write(String.valueOf(str.getArrayDouble().get(i)) + "\n");
                if (str.getSizeString() != 0)
                    for (int i = 0; i < str.getSizeString(); ++i)
                        writerString.write(str.getArrayString().get(i) + "\n");
            } catch (IOException e) {
                log.warn("Ошибка при записи в файл.");
            }
        } else log.error("Файлы для анализа не указаны.");
    }

//    private static void processLine(String line, ArrayList<Double> arrayDouble,ArrayList<Integer> arrayInt,ArrayList<String> arrayString) {
//
// Str str = new Str();
//        try {
//            if (Str.checkInt(line)) writerInt.write(line + "\n");
//            else if (Str.checkFloat(line)) writerFloat.write(line + "\n");
//            else if (Str.checkString(line)) writerString.write(line + "\n");
//            //else logger.warning("error: the string contains numbers and symbols");
//        } catch (IOException e) {
//            //logger.severe("Error writing to file: " + e.getMessage());
//        }

    //}
//    private static BufferedWriter openWriter(com.example.Flags flags, String result_file) {


//    private static BufferedWriter openWriter(com.example.Flags flags, String result_file) {
//        if (flags.a) {
//            try {
//                return new BufferedWriter(new FileWriter(result_file, true));
//            } catch (IOException var3) {
//                throw new RuntimeException("error: when opening the results file[" + result_file + "] for additional recording");
//            }
//        } else {
//            try {
//                return new BufferedWriter(new FileWriter(result_file));
//            } catch (IOException var4) {
//                throw new RuntimeException("error: when opening the results file[" + result_file + "] for overwriting");
//            }
//        }
//    }

//        private static void close_writer (BufferedWriter writer){
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException var2) {
//                    System.out.println("error:close file");
//                }
//            }
//        }
}
