package tasks;

import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;

public class Deadline extends Task {
    private final String by;

    public Deadline(String taskName, String by, boolean isCompleted) {
        super(taskName, isCompleted);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getStringRepresentation() {
        return "deadline " + taskName + " /by " + this.by + getIsCompletedString();
    }

    public static Deadline getTask (String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = currentInput.replaceAll(Task.IS_COMPLETED_STRING, "");

            int idxOfDeadline = currentInput.indexOf(" /by ");
            if (idxOfDeadline == -1) {
                throw new InvalidTaskArguments();
            }
            // Extract after _deadline_, which is 8 characters long
            String taskName = currentInput.substring(8, idxOfDeadline);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            // Extract after _ /by _, which is 5 characters long
            String by = currentInput.substring(idxOfDeadline + 5);
            by = by.trim();

            return new Deadline(taskName, by, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }
    }
}
