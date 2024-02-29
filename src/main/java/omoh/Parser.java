package omoh;

import omoh.customexceptions.EmptyFindException;
import omoh.customexceptions.EmptyTaskNumberException;
import omoh.customexceptions.IllegalDeadlineInput;
import omoh.customexceptions.IllegalEventInput;
import omoh.tasktypes.Deadline;
import omoh.tasktypes.Event;

public class Parser {
    /**
     * Extracts the task number from the input string.
     * Used when user wants to mark tasks or delete tasks
     *
     * @param input The input string containing the task number.
     * @return The extracted task number.
     * @throws NumberFormatException if the task number is not a valid integer.
     * @throws EmptyTaskNumberException if the input string is empty after removing the keyword.
     */
    public static int extractTaskNumber(String input) throws NumberFormatException, EmptyTaskNumberException {
        String keyword;
        if (input.startsWith("mark")) {
            keyword = "mark";
        } else if (input.startsWith("unmark")) {
            keyword = "unmark";
        } else {
            keyword = "delete";
        }
        String numberString = input.substring(keyword.length()).trim();
        if (numberString.isEmpty()) {
            throw new EmptyTaskNumberException();
        }
        int taskNumber = Integer.parseInt(numberString);
        return taskNumber;
    }

    /**
     * Extracts the task description and due date for a Deadline task from the input string.
     *
     * @param input The input string containing the task description and due date.
     * @return The extracted Deadline task with its description and due date.
     * @throws IllegalDeadlineInput if the input string does not follow the required format.
     */
    public static Deadline extractTaskAndDueDate (String input) throws IllegalDeadlineInput {
        //splits string according to /by keyword
        String[] parts = input.split("/by");
        //if array is not size 2, means that we are missing inputs
        if (parts.length != 2) {
            throw new IllegalDeadlineInput();
        }
        String deadlineDescription = parts[0].substring("deadline".length()).trim();
        String deadlineDueBy = parts[1].trim();
        Deadline taskAndDeadlineString = new Deadline(deadlineDescription, deadlineDueBy);
        if (deadlineDescription.isEmpty() || deadlineDueBy.isEmpty()) {
            throw new IllegalDeadlineInput();
        }
        return taskAndDeadlineString;
    }

    /**
     * Extracts the event details from the input string.
     *
     * @param input The input string containing the event description, start date, and end date.
     * @return The extracted Event object with its description, start date, and end date.
     * @throws IllegalEventInput if the input string does not follow the required format.
     */
    public static Event extractEvent (String input) throws IllegalEventInput {
        //splits string according to /by keyword
        String[] parts = input.split("/from|/to");
        if (parts.length < 3) {
            throw new IllegalEventInput();
        }
        //extracts task portion from input, after deadline keyword
        String eventDescription = parts[0].substring("event".length()).trim();
        //extracts from and to portion from input
        String eventFrom = parts[1].trim();
        String eventTo = parts[2].trim();

        Event eventDetails  = new Event(eventDescription, eventFrom, eventTo);

        if (eventFrom.isEmpty() || eventTo.isEmpty()) {
            throw new IllegalEventInput();
        }
        return eventDetails;
    }

    //extract keyword to find
    public static String extractKeyword(String input) throws EmptyFindException {
        //split by empty space
        String[] parts = input.split(" ");
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new EmptyFindException();
        }
        //parts[1] contains the keyword
        return parts[1];
    }
}
