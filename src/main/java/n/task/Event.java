/**
 * Represents an event task, a specific type of Task.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */
package n.task;
public class Event extends Task{
    private final String fromTime;
    private final String toTime;
    /**
     * Constructor to initialize an Event object.
     *
     * @param description The description of the event task.
     * @param arrayIndex  The index of the task in the array.
     */
    public Event(String description, int arrayIndex) {
        // Call the constructor of the base class (Task) to initialize common fields
        super(description, arrayIndex);
        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");
        // Extract the task description, start time, and end time from the input
        this.description = description.substring(0,fromIndex).trim();
        this.fromTime = description.substring(fromIndex + 5, toIndex).trim();
        this.toTime = description.substring(toIndex+3).trim();
        this.taskType = Type.Event;
    }
    /**
     * Overrides the default toString method to represent the event task as a string.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return this.getIndex()+ ". [E] ["+ this.getCheckMark()+"] "
                +this.getDescription()+ " (from: " +fromTime+ " to: " +toTime+ ")";
    }
}
