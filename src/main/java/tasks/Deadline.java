package tasks;

import exceptions.DuckInvalidDeadlineDescriptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Represents Deadline that is a subclass of Task
 * Contains description of task and
 * by with is a string of when the task is supposed to be done by
 */
public class Deadline extends Task {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    protected String by;

    /**
     * Constructs Deadline class
     * @param description name of deadline
     * @param by string of when the deadline is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Adds a new Deadline task to the list of tasks based on user input.
     *
     * @param tasks The ArrayList containing the tasks.
     * @param userInput The user input specifying the new Deadline task.
     * @param index number of tasks in arraylist
     * @return The updated index after adding the new Deadline task.
     */
    public static int addDeadline(ArrayList<Task> tasks, String userInput, int index) {
        try {
            String[] split = userInput.split(" /");
            if (split.length != 2 || !split[1].startsWith("by ")) { //throws exception if the inputs are not to program specifications
                throw new DuckInvalidDeadlineDescriptionException();
            }
            Deadline newDeadline = new Deadline(split[0].substring(9), split[1].substring(3));
            tasks.add(newDeadline);
            System.out.println(LINE_SEPARATOR);
            System.out.println(ADDED_MESSAGE + tasks.get(index));
            index++;
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch (DuckInvalidDeadlineDescriptionException e) {
            System.out.println("Invalid Event input. Please type in format: deadline [string] /by [string]");
        }
        return index;
    }

    /**
     * Appends the Deadline task's information in the Duck Data File format.
     *
     * @param deadline The Deadline task to append to the Duck Data File.
     * @return The formatted string for storing the Deadline task in the Duck Data File.
     */
    public static String appendDeadlineDuckDataFile(Deadline deadline) {
        return "D | " + deadline.getDescription() + " | by: " + deadline.by + "\n";
    }

    /**
     * Returns the string representation of the Deadline task, including its type indicator "[D]"
     * and information about the deadline.
     *
     * @return The formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}