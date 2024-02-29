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

    public static String getSampleEvent() {
        return Keywords.EVENT + " <task name>" + Keywords.FROM + "<start time>" + Keywords.TO + "<end time>"
                + System.lineSeparator() + "For example, " + Keywords.EVENT + " project meeting" + Keywords.FROM
                + "2/12/2021 2pm" + Keywords.TO + "2/12/2021 4pm";
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

    /**
     * Parses the input to get the task name, start and end.
     * @param currentInput the input to be parsed
     * @return an Event task
     * @throws EmptyTaskDescription if the task description is empty
     * @throws InvalidTaskArguments if the format of the event is invalid
     */
    public static Event getTask(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = Task.removeIsCompletedString(currentInput);

            int idxOfStart = currentInput.indexOf(Keywords.FROM);
            int idxOfEnd = currentInput.indexOf(Keywords.TO);
            boolean hasStartEnd = idxOfStart != -1 && idxOfEnd != -1;
            boolean isStartBeforeEnd = idxOfStart < idxOfEnd;
            if (!hasStartEnd || !isStartBeforeEnd) {
                throw new InvalidTaskArguments(getSampleEvent());
            }

            String taskName = currentInput.substring(Keywords.EVENT.length(), idxOfStart);
            taskName = taskName.trim();

            String start = currentInput.substring(idxOfStart + Keywords.FROM.length(), idxOfEnd);
            start = start.trim();

            String end = currentInput.substring(idxOfEnd + Keywords.TO.length());
            end = end.trim();

            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }
            return new Event(taskName, start, end, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments(getSampleEvent());
        }

    }
}
