package cody.parser;

import cody.CodyException;
import cody.TaskList;
import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

import cody.ui.Ui;

public class Parser {

    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;

    public static String parseCommand(String input, TaskList taskList) {
        if (input.equals("list")) {
            return taskList.printList();
        } else if (input.startsWith("delete")) {
            return taskList.deleteTask(input);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            return taskList.handleMarking(input);
        } else if (input.startsWith("find")) {
            return taskList.findTask(input);
        } else {
            return taskList.addTask(input);
        }
    }

    public static Task createTaskFromInput(String input) throws CodyException {
        if (input.startsWith("todo")) {
            return createTodoTask(input);
        } else if (input.startsWith("deadline")) {
            return createDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return createEventTask(input);
        } else {
            throw new CodyException("I'm not sure what you mean. "
                    + "Please use 'todo', 'deadline', or 'event' to add tasks");
        }
    }

    private static Todo createTodoTask(String input) throws CodyException {
        if (input.length() <= TODO_PREFIX_LENGTH) {
            throw new CodyException("The description of a todo cannot be empty. Please use 'todo <description>'");
        }

        String description = input.substring(5).trim(); // Removing 'todo ' prefix.
        return new Todo(description);
    }

    private static Deadline createDeadlineTask(String input) throws CodyException {
        if (input.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new CodyException("The description of a deadline cannot be empty. "
                    + "Please use 'deadline <description> /by <deadline>'");
        }

        String[] deadlineDetails = input.split(" /by ", 2);
        if (deadlineDetails.length < 2) {
            throw new CodyException("Invalid deadline format. Please use 'deadline <description> /by <deadline>'");
        }

        String description = deadlineDetails[0].substring(9).trim(); // Removing 'deadline ' prefix.
        String by = deadlineDetails[1];
        return new Deadline(description, by);
    }

    private static Event createEventTask(String input) throws CodyException {
        if (input.length() <= EVENT_PREFIX_LENGTH) {
            throw new CodyException("The description of an event cannot be empty. "
                    + "Please use 'event <description> /from <start time> /to <end time>'");
        }

        String[] eventDetails = input.split(" /from | /to ", 3);
        if (eventDetails.length < 3) {
            throw new CodyException("Invalid event format. "
                    + "Please use 'event <description> /from <start time> /to <end time>'");
        }

        String description = eventDetails[0].substring(6).trim(); // Removing 'event ' prefix.
        String from = eventDetails[1];
        String to = eventDetails[2];
        return new Event(description, from, to);
    }
}
