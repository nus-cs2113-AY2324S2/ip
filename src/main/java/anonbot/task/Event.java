package anonbot.task;

import anonbot.misc.Parser;

public class Event extends Task {
    private String from;
    private String to;

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
