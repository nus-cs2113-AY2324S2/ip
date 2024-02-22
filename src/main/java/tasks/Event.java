package tasks;

import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import ui.Keywords;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String taskName, String start, String end, boolean isCompleted) {
        super(taskName, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String getStringRepresentation() {
        return Keywords.EVENT + " " + taskName + Keywords.FROM + this.start +
                Keywords.TO + this.end + getIsCompletedString();
    }

    public static Event getTask(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = currentInput.replaceAll(Task.IS_COMPLETED_STRING, "");

            int idxOfStart = currentInput.indexOf(Keywords.FROM);
            int idxOfEnd = currentInput.indexOf(Keywords.TO);
            boolean hasStartEnd = idxOfStart != -1 && idxOfEnd != -1;
            if (!hasStartEnd) {
                throw new InvalidTaskArguments();
            }

            String taskName = currentInput.substring(Keywords.EVENT.length(), idxOfStart);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            String start = currentInput.substring(idxOfStart + Keywords.FROM.length(), idxOfEnd);
            start = start.trim();

            String end = currentInput.substring(idxOfEnd + Keywords.TO.length());
            end = end.trim();

            return new Event(taskName, start, end, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }
}
