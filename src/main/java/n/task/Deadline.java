/**
 * Represents a deadline task, a specific type of Task.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */
package n.task;

import n.exceptions.EmptyTaskDescriptionException;

public class Deadline extends Task{
    private String deadline;
    /**
     * Constructor to initialize a Deadline object.
     *
     * @param description The description of the deadline task.
     * @param arrayIndex  The index of the task in the array.
     * @throws EmptyTaskDescriptionException If the task description or deadline is empty.
     */
    public Deadline(String description, int arrayIndex) throws EmptyTaskDescriptionException {
        // Call the constructor of the base class (Task) to initialize common fields
        super(description, arrayIndex);
        int deadlineIndex = description.indexOf("/by");
        // Extract the task description and deadline from the input
        this.description = description.substring(0,deadlineIndex).trim();
        this.deadline = description.substring(deadlineIndex + 3).trim();
        // Check if the description or deadline is empty, throw an exception if so
        if (this.description.isEmpty() || this.deadline.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        this.taskType = Type.Deadline;
    }
    /**
     * Getter method to retrieve the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getDeadline() {
        return deadline;
    }
    /**
     * Setter method to update the deadline of the task.
     *
     * @param deadline The new deadline for the task.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    /**
     * Overrides the default toString method in Task to represent the deadline task as a string.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return this.getIndex()+ ". [D] ["+ this.getCheckMark()+"] "
                +this.getDescription()+ "(by: " +this.deadline+ ")";
    }
}
