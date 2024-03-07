package Bobble;

import Bobble.task.Deadline;
import Bobble.task.Event;

public class Parser {
    public static String[] getCommandAndDesc(String input) {
        return input.split(" ", 2);
    }

    public static int getTaskNumber(String userInput, String command) {
        if (command.equals("mark")) {
            return Integer.parseInt(userInput.substring(5)) - 1;
        }
        return Integer.parseInt(userInput.substring(7)) - 1;
    }

    public static Deadline getNewDeadline(String Description) {
        String[] parts = Description.split("/by");
        return new Deadline(parts[0], parts[1]);
    }

    static Event getNewEvent(String description) {
        String[] parts = description.split("/from");
        String[] duration = parts[1].split("/to");
        return new Event(parts[0], duration[0], duration[1]);
    }
}
