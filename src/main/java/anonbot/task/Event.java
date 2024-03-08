package anonbot.task;

import anonbot.misc.Parser;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new event task. The `from` and/or `to` parameter(s) are optional
     *
     * @param eventDescription The description for the event, including the `from` and/or `to` parameters.
     * @param taskNumber The task number to assign this event task.
     */
    public Event(String eventDescription, int taskNumber) {
        super("", taskNumber, TaskType.EVENT);
        String[] formattedEventDescription = Parser.parseEventDescription(eventDescription);
        setTaskDescription(formattedEventDescription[0]);
        setFrom(formattedEventDescription[1]);
        setTo(formattedEventDescription[2]);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    @Override
    public void printTask() {
        System.out.format("%d.[E][%c] %s (from: %s to: %s)" + System.lineSeparator(),
                getTaskNumber(),
                isTaskDone() ? 'X' : ' ',
                getTaskDescription(),
                getFrom(),
                getTo());
    }

}
