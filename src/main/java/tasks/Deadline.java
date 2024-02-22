package tasks;

import exceptions.DuckInvalidDeadlineDescriptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Deadline extends Task {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static int addDeadline(ArrayList<Task> tasks, String userInput, int index) {
        try {
            String[] split = userInput.split("/");
            if (split.length != 2 || !split[1].startsWith("by ")) { //throws exception if the inputs are not to program specifications
                throw new DuckInvalidDeadlineDescriptionException();
            }
            Deadline newDeadline = new Deadline(split[0].substring(9), split[1].substring(3));
            appendDeadlineDuckDataFile(newDeadline);
            tasks.add(newDeadline);
            System.out.println(LINE_SEPARATOR);
            System.out.println(ADDED_MESSAGE + tasks.get(index));
            index++;
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch (DuckInvalidDeadlineDescriptionException e) {
            System.out.println("Invalid Event input. Please type in format: deadline [string] /by [string]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return index;
    }

    public static void appendDeadlineDuckDataFile(Deadline deadline) throws IOException {
        String lineToAdd = "D | " + getDescription() + "| by: " + deadline.by + "\n";
        System.out.println(lineToAdd);
        Files.write(FILE_PATH, lineToAdd.getBytes(), StandardOpenOption.APPEND);
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

}