package Alexis.task;

import Alexis.console.Ui;


/**
 * The Event class represents an event task with a specific start and end date or time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event object from a user input string.
     * The input must contain a description, a start date and an end date separated by keywords "/from" and "/to"
     * respectively.
     *
     * @param input User input string for the Deadline object.
     * @return Event object or null if the input format is incorrect.
     * @throws IndexOutOfBoundsException If keywords "/from" or "/to" is missing.
     */
    protected static Event getEvent(String input) {
        String keywordFrom = "/from";
        String keywordTo = "/to";
        int keywordFromIndex = input.indexOf(keywordFrom);
        int keywordToIndex = input.indexOf(keywordTo, keywordFromIndex + keywordFrom.length());
        try {
            String description = input.substring(0, keywordFromIndex).trim();
            String taskStart = input.substring(keywordFromIndex + keywordFrom.length(), keywordToIndex).trim();
            String taskEnd = input.substring(keywordToIndex + keywordTo.length()).trim();
            return new Event(description, taskStart, taskEnd);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.MISSING_EVENT_START_OR_END_DATE_ERROR);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return A string representation of the task with symbol "E" denoting task type.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)\n", super.toString(), this.start, this.end);
    }
}

