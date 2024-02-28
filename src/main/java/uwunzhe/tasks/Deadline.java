package uwunzhe.tasks;

import java.time.LocalDate;

import uwunzhe.handler.DateHandler;

public class Deadline extends Task {
    private LocalDate end;

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline.
     * @param end The end time of the deadline.
     */
    public Deadline(String name, LocalDate end) {
        super(name);
        this.type = TaskType.DEADLINE.getType();
        this.end = end;
    }

    /**
     * Constructor for Deadline.
     * 
     * @param name The name of the deadline.
     * @param end The end time of the deadline.
     * @param isDone The status of the deadline.
     */
    public Deadline(String name, LocalDate end, boolean isDone) {
        super(name);
        this.type = TaskType.DEADLINE.getType();
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
        String endString = DateHandler.formatDate(this.end);
        return String.format("[%s][%s] %s (by: %s)",
                this.type, completion, this.name, endString);
    }

    /**
     * Returns the string representation of the task for storage.
     * 
     * @param delimiter The delimiter to be used.
     * @return String representation of the task for storage.
     */
    public String toStorageString(String delimiter) {
        int completion = this.isDone ? 1 : 0;
        String endString = DateHandler.dateToStorageString(this.end);

        return String.format("%s%s%d%s%s%s%s",
                this.type, delimiter, completion, delimiter, this.name, delimiter, endString);
    }

    /**
     * Returns the end time of the deadline as a LocalDate type.
     * 
     * @return The end time of the deadline.
     */
    public LocalDate getDeadline() {
        return this.end;
    }
}
