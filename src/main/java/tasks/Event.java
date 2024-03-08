package tasks;

import customexceptions.IncompletePromptException;

import java.time.format.DateTimeParseException;

/** Most complex type of task with a timeframe between two dates. */
public class Event extends Task {

    /**
     * Constructs a event object, initially does not have event dates yet.
     *
     * @param task The event task
     */
    public Event(String task) {
        super(task);
        this.isEvent = false;
        setTaskType("E");
    }
    private boolean isEvent;

    /**
     * Marks the task as an event and not other tasks.
     *
     * @param event True if the task is an event, false otherwise.
     */
    public void setEvent(boolean event) {
        isEvent = event;
    }
    private String eventFrom, eventTo;

    public String getEventFrom() {
        return eventFrom;
    }

    /**
     * Sets the start event date based on a specific date format of "d MMM yyyy".
     *
     * @param eventFrom Start date.
     */
    public void setEventFrom(String eventFrom) throws IncompletePromptException {
        try {
            this.eventFrom = parseStringToDate(eventFrom);
        } catch (DateTimeParseException e) {
            System.out.println("Sorry, the event date could not be parsed.");
            throw new IncompletePromptException(false);
        }
    }

    public String getEventTo() {
        return eventTo;
    }

    /**
     * Sets the end event date based on specific date format "d MMM yyyy"
     *
     * @param eventTo End date.
     */
    public void setEventTo(String eventTo) throws IncompletePromptException {
        try {
            this.eventTo = parseStringToDate(eventTo);
        } catch (DateTimeParseException e) {
            System.out.println("Sorry, the event date could not be parsed.");
            throw new IncompletePromptException(false);
        }
    }

    /** Prints the event information such as the description, start time and end time when presented by UI. */
    @Override
    public void print() {
        String additionalInfo;
        if (isEvent) {
            additionalInfo = " (from: " + eventFrom + " to: " + eventTo + ")";
        } else {
            additionalInfo = "";
        }
        System.out.print('[' + getTaskType() + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTaskDescription() + additionalInfo);
    }
}