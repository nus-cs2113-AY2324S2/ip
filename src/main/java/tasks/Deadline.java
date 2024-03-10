package tasks;

import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import ui.Keywords;

public class Deadline extends Task {
    private final String by;

    public Deadline(String taskName, String by, boolean isCompleted) {
        super(taskName, isCompleted);
        this.by = by;
    }

    public static String getSampleDeadline() {
        return Keywords.DEADLINE + " <task name>" + Keywords.BY + "<deadline>" + System.lineSeparator()
                + "For example, " + Keywords.DEADLINE + " return book" + Keywords.BY + "June 6th";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getStringRepresentation() {
        return Keywords.DEADLINE + " "+ taskName + Keywords.BY + this.by + getIsCompletedString();
    }

    /**
     * Parses the input to get the task name and deadline.
     * @param currentInput the input to be parsed
     * @return a Deadline task
     * @throws EmptyTaskDescription if the task description is empty
     * @throws InvalidTaskArguments if the format of the deadline is invalid
     */
    public static Deadline getTask (String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = Task.removeIsCompletedString(currentInput);

            int idxOfDeadline = currentInput.indexOf(Keywords.BY);
            boolean hasDeadline = idxOfDeadline != -1;
            if (!hasDeadline) {
                throw new InvalidTaskArguments(getSampleDeadline());
            }

            String taskName = currentInput.substring(Keywords.DEADLINE.length(), idxOfDeadline);
            taskName = taskName.trim();

            String by = currentInput.substring(idxOfDeadline + Keywords.BY.length());
            by = by.trim();

            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }
            return new Deadline(taskName, by, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments(getSampleDeadline());
        }
    }
}
