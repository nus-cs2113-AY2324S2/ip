package omoh;

import omoh.customexceptions.EmptyTaskNumberException;
import omoh.customexceptions.IllegalDeadlineInput;
import omoh.tasktypes.Deadline;

public class Parser {

    //method that extracts the task number to mark or unmark
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

    //returns the Task and due date for Deadline
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


}
