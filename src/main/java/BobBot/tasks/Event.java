package BobBot.tasks;

import BobBot.exceptions.InvalidEventException;

/**
 * Implements an event task that stores the description of the task and the
 * duration of the task.
 * 
 * <p> The description is stored in the format <code>[task] /from [start] /to [end]</code>.</p>
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class Event extends Task {

    protected String task;
    protected String from;
    protected String to;

    /**
     * Creates an event task with the given description.
     * 
     * @param description The description of the event task.
     * @throws InvalidEventException If the task description is empty or 
     * does not contain a start or end date/time.
     */
    public Event(String description) throws InvalidEventException {
        super(description);

        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidEventException();
        }

        this.task = this.description.substring(
                "event".length(), 
                this.description.indexOf("/from")
                ).trim();
        this.from = this.description.substring(
                this.description.indexOf("/from") + "/from".length(), 
                this.description.indexOf("/to")
                ).trim();
        this.to = this.description.substring(
                this.description.indexOf("/to") + "/to".length()
                ).trim();


        if (this.task.length() == 0 || this.from.length() == 0 || this.to.length() == 0) {
            throw new InvalidEventException();
        }
    }
    
    /**
     * Returns the string representation of the event task.
     * 
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " 
                + this.task 
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
