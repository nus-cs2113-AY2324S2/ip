package tasks;

import exceptions.DuckInvalidEventDescriptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Event extends Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    public static int addEvent(ArrayList<Task> tasks, String userInput, int index){
        try {
            String[] split = userInput.split(" /");
            if (split.length != 3 || !split[1].startsWith("from ") || !split[2].startsWith("by ")) { //throws exception if the inputs are not to program specifications
                throw new DuckInvalidEventDescriptionException();
            }
            Event newEvent = new Event(split[0].substring(6), split[1].substring(5).trim(), split[2].substring(3));
            tasks.add(newEvent);
            System.out.println(LINE_SEPARATOR);
            System.out.println(ADDED_MESSAGE + tasks.get(index));
            index++;
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch (DuckInvalidEventDescriptionException e) {
            System.out.println("Invalid Event input. Please type in format: event [string] /from [string] /by [string]");
        }

        return index;
    }

    /**
     * Add Event into duck.txt file in format
     */
    public static String appendEventDuckDataFile(Event event) {
        String lineToAdd = "E | " + event.getDescription() + " | from: " + event.from + "| by: " + event.by + "\n";
        return lineToAdd;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + this.by + ")";
    }
}
