package com.example;

import org.apache.commons.cli.*;

public class Parser {
    private static final String flagO = "o", flagP = "p", flagS = "s", flagF = "f", flagsA = "a";

    public CommandLine parseArguments(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(flagsA,"short-option",false,"no argument");
        options.addOption(flagS,"short-option",false,"no argument");
        options.addOption(flagF,"another-option",false,"yes argument");
        options.addOption(flagO,"short-option",true,"no argument");
        options.addOption(flagP,"prefix",true,"with prefix");
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options,args);

    }
}
