/**
 * The TaskFactory class is responsible for creating Task objects from user input
 * and handling the parsing of task data from file.
 */

import java.time.format.DateTimeParseException;
 
public class TaskFactory {
    /**
     * Creates a Task object based on the user input.
     *
     * @param input The user input string containing the task type and details
     * @return The created Task object, or null if the input is invalid
     * @throws IncyException If the task type or format is invalid
     */
    static Task createTask(String input) throws IncyException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts.length > 1 ? parts[1] : "";

        switch (command.toLowerCase()) {
            case "todo":
                if (taskInfo.isEmpty()) {
                    throw new IncyException("Oi! You can't have a todo with no description!");
                }
                return new Todo(taskInfo);
            case "deadline":
                return createDeadline(taskInfo);
            case "event":
                return createEvent(taskInfo);
            default:
                throw new IncyException("Hol' up bruv, I dun get what that means..., try typin 'todo', 'deadline', or 'event' first, yeah?");
        }
    }

    /**
     * Creates a Task object from a line in the data file.
     *
     * @param line The line from the data file containing the task data
     * @return The created Task object, or null if the line is invalid
     */
    static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ", 3);
        if (parts.length < 3) {
            return null;
        }
        String typeCode = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskData = parts[2];
        switch (typeCode) {
            case "T":
                Todo todo = new Todo(taskData);
                todo.setDone(isDone);
                return todo;
            case "D":
                return createDeadlineFromLine(taskData, isDone);
            case "E":
                return createEventFromLine(taskData, isDone);
            default:
                return null;
        }
    }

    /**
     * Creates a Deadline object from the user input string.
     * @param taskInfo The user input string containing the task description and deadline
     * @return The created Deadline object, or null if the input is invalid
     */
    private static Task createDeadline(String taskInfo) {
        String[] deadlineParts = taskInfo.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            printFormatError("You've mucked up the deadline format. Do it like 'deadline [task] /by [yyyy-MM-dd HHmm]', yeah?");
            return null;
        }
        try {
            return new Deadline(deadlineParts[0], deadlineParts[1]);
        } catch (DateTimeParseException e) {
            printFormatError("Invalid date format for deadline. Use 'yyyy-MM-dd HHmm' format, mate.");
            return null;
        }
    }
    
    /**
     * Creates an Event object from the user input string.
     * @param taskInfo The user input string containing the task description and event details
     * @return The created Event object, or null if the input is invalid
     */
    private static Task createEvent(String taskInfo) {
        String[] eventParts = taskInfo.split(" /from ", 2);
        String[] eventTime = eventParts.length > 1 ? eventParts[1].split(" /to ", 2) : new String[0];
        if (eventParts.length < 2 || eventTime.length < 2) {
            printFormatError("You've bungled the event format. It's gotta be 'event [task] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]', innit?");
            return null;
        }
        try {
            return new Event(eventParts[0], eventTime[0], eventTime[1]);
        } catch (DateTimeParseException e) {
            printFormatError("Invalid date format for event. Use 'yyyy-MM-dd HHmm' format, mate.");
            return null;
        }
    }

    /**
     * Creates a Deadline object from a line in the data file.
     * @param line The line from the data file representing a Deadline task
     * @return The created Deadline object, or null if the line is invalid
     */
    private static Deadline createDeadlineFromLine(String line, boolean isDone) {
        String[] parts = line.split(" \\| ", 2);
        if (parts.length < 2) {
            return null;
        }
        Deadline deadline = new Deadline(parts[0], parts[1]);
        deadline.setDone(isDone);
        return deadline;
    }

    /**
     * Creates an Event object from a line in the data file.
     * @param line The line from the data file representing an Event task
     * @return The created Event object, or null if the line is invalid
     */
    private static Event createEventFromLine(String line, boolean isDone) {
        String[] parts = line.split(" \\| ", 3);
        if (parts.length < 3) {
            return null;
        }
        Event event = new Event(parts[0], parts[1], parts[2]);
        event.setDone(isDone);
        return event;
    }

    /**
     * Prints a format error message to the console.
     * @param errorMessage The error message to print
     */
    static void printFormatError(String errorMessage) {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "Error: " + errorMessage + "\n" +
                Constants.LINE_STRING_BOTTOM);
    }
}
