package oley.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents methods that are used to parse the instructions.
 * It deals with making sense of the user command, e.g. split the sentence into an array of String and return
 * its first element for the recognition of different types of commands.
 */
public class Parser {
    /**
     * Returns the first word by splitting the sentence entered by the user.
     *
     * @param sentence Sentence entered by the user as a command.
     * @return The first element in the array of strings entered by the user.
     */
    public static String parse(String sentence) {
        String[] markInstructions = sentence.split(" ");
        return markInstructions[0];
    }

    /**
     * Returns the index of task to be deleted or marked or unmarked by splitting the sentence entered by the user.
     * The second element of the array of strings will be the index + 1, and it will be transferred to Integer type.
     *
     * @param sentence Sentence entered by the user as a command.
     * @return The index of task to be deleted or marked or unmarked.
     */
    public static int parseDeleteOrMark(String sentence) {
        String[] parseInstructions = sentence.split(" ");
        return Integer.parseInt(parseInstructions[1]) - 1;
    }

    /**
     * Returns the time the user want to search for by splitting the sentence entered by the user.
     * The second element of the array of strings will be the time.
     *
     * @param sentence Sentence entered by the user as a command.
     * @return The time in "yyyy-MM-dd-HHmm" format.
     */
    public static LocalDateTime parseTiming(String sentence) {
        String[] parseInstructions = sentence.split(" ");
        return LocalDateTime.parse(parseInstructions[1], DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
    }

    /**
     * Returns the keyword that user want to search for by splitting the sentence entered by the user.
     * The second element of the array of strings will be the keyword.
     *
     * @param sentence Sentence entered by the user as a command.
     * @return The keyword that user want to search for.
     */
    public static String parseFind(String sentence) {
        String[] parseInstructions = sentence.split(" ");
        return parseInstructions[1];
    }
}
