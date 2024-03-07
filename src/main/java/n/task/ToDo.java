/**
 * Represents a to-do task, a specific type of Task.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */
package n.task;

import n.exceptions.EmptyTaskDescriptionException;

public class ToDo extends Task{
    /**
     * Constructor to initialize a ToDo object.
     *
     * @param description The description of the to-do task.
     * @param arrayIndex  The index of the task in the array.
     * @throws EmptyTaskDescriptionException If the task description is empty.
     */
    public ToDo(String description, int arrayIndex) throws EmptyTaskDescriptionException {
        // Call the constructor of the base class (Task) to initialize common fields
        super(description, arrayIndex);
        if (this.description.isEmpty()){
            throw new EmptyTaskDescriptionException();
        }
        this.taskType = Type.ToDo;
    }
    /**
     * Overrides the toString method in Task to represent the to-do task as a string.
     *
     * @return A formatted string representation of the to-do task.
     */
    @Override
    public String toString() {
        return this.getIndex()+ ". [T] ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}
