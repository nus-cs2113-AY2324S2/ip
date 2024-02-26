package Alexis.task;

import Alexis.console.Ui;

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

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)\n", super.toString(), this.start, this.end);
    }
}

