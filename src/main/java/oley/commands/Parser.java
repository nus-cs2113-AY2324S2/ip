package oley.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String parse(String sentence) {
        String[] markInstructions = sentence.split(" ");
        return markInstructions[0];
    }

    public static int parseDeleteOrMark(String sentence) {
        String[] parseInstructions = sentence.split(" ");
        return Integer.parseInt(parseInstructions[1]) - 1;
    }

    public static LocalDateTime parseTiming(String sentence) {
        String[] parseInstructions = sentence.split(" ");
        return LocalDateTime.parse(parseInstructions[1], DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
    }

    public static String parseFind(String sentence) {
        String[] parseInstructions = sentence.split(" ");
        return parseInstructions[1];
    }
}
