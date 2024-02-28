package uwunzhe.tasks;

import java.time.LocalDate;

import uwunzhe.handler.DateHandler;

public class Event extends Task {
    private LocalDate start, end;

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.type = TaskType.EVENT.getType();
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for Event.
     * 
     * @param name The name of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     * @param isDone The status of the event.
     */
    public Event(String name, LocalDate start, LocalDate end, boolean isDone) {
        super(name);
        this.type = TaskType.EVENT.getType();
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    /**
     * Prints the task and its status in one line.
     * 
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String completion = this.isDone ? "X" : " ";
        String startString = DateHandler.formatDate(this.start);
        String endString = DateHandler.formatDate(this.end);

        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.type, completion, this.name, startString, endString);
    }

    /**
     * Returns the string representation of the task for storage.
     * 
     * @param delimiter The delimiter to be used.
     * @return String representation of the task for storage.
     */
    public String toStorageString(String delimiter) {
        int completion = this.isDone ? 1 : 0;
        String startString = DateHandler.dateToStorageString(this.start);
        String endString = DateHandler.dateToStorageString(this.end);

        return String.format("%s%s%d%s%s%s%s%s%s",
                this.type, delimiter, completion, delimiter, this.name,
                delimiter, startString, delimiter, endString);
    }

    /**
     * Returns the start time of the event as a LocalDate type.
     * 
     * @return The start time of the event.
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Returns the end time of the event as a LocalDate type.
     * 
     * @return The end time of the event.
     */
    public LocalDate getEnd() {
        return this.end;
    }
}
