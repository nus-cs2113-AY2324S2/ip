package Bobble;

import Bobble.task.Deadline;
import Bobble.task.Event;

/**
 * The Parser class is responsible for parsing user input and creating new tasks objects.
 */
public class Parser {
    /**
     * Parses the input string into a command and a description.
     *
     * @param input The input string to be split.
     * @return An array containing the command and the description.
     */
    public static String[] getCommandAndDescription(String input) {
        return input.split(" ", 2);
    }

    /**
     * Creates new Deadline task based on the description of the user input.
     *
     * @param Description The description of the user input, which includes the description and the deadline of a
     *                    Deadline task.
     * @return A new Deadline task.
     */
    public static Deadline getNewDeadline(String Description) {
        String[] parts = Description.split("/by");
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Creates a new Event task based on the description of the user input.
     *
     * @param description The description of the user input, which includes the description and the timeframe of an
     *                    Event task.
     * @return A new Event task.
     */
    static Event getNewEvent(String description) {
        String[] parts = description.split("/from");
        String[] duration = parts[1].split("/to");
        return new Event(parts[0], duration[0], duration[1]);
    }
}
