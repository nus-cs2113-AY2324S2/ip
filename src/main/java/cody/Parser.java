package cody;

import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

public class Parser {

    public static Task createTaskFromInput(String input) throws CodyException {
        if (input.startsWith("todo")) {
            return createTodoTask(input);
        } else if (input.startsWith("deadline")) {
            return createDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return createEventTask(input);
        } else {
            // If the task type is unknown, throw a CodyException
            throw new CodyException(" I'm not sure what you mean. "
                    + "Please use 'todo', 'deadline' or 'event' to add tasks");
        }
    }

    private static Todo createTodoTask(String input) throws CodyException {
        if (input.length() <= 5) {
            throw new CodyException(" The description of a todo cannot be empty."
                    + " Please use 'todo <description>'");
        }

        String description = input.substring(5).trim(); // Removing 'todo ' prefix.
        return new Todo(description);
    }

    private static Deadline createDeadlineTask(String input) throws CodyException {
        if (input.length() <= 9) {
            throw new CodyException(" The description of a deadline cannot be empty."
                    + " Please use 'deadline <description> /by <deadline>'");
        }

        String[] deadlineDetails = input.split(" /by ", 2);
        String description = deadlineDetails[0].substring(9).trim(); // Removing 'deadline ' prefix.
        String by = deadlineDetails.length > 1 ? deadlineDetails[1] : "No deadline specified";
        return new Deadline(description, by);
    }

    private static Event createEventTask(String input) throws CodyException {
        if (input.length() <= 6) {
            throw new CodyException(" The description of an event cannot be empty."
                    + " Please use 'event <description> /from <start time> /to <end time>'");
        }

        String[] eventDetails = input.split(" /from | /to ");
        String description = eventDetails[0].substring(6).trim(); // Removing 'event ' prefix.
        String from = (eventDetails.length > 1) ? eventDetails[1] : "No start time specified";
        String to = (eventDetails.length > 2) ? eventDetails[2] : "No end time specified";
        return new Event(description, from, to);
    }
}

