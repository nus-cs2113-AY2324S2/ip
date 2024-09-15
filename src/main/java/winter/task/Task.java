package winter.task;


/**
 * Represents a task. A <code>Task</code> object can be inherited by its child classes,
 * <code>Todo</code>, <code>Deadline</code> and <code>Event</code>
 */

import java.time.LocalDateTime;


public class Task {
    private boolean isMarked;
    private int order;
    private String taskName;

    private String doneCheckbox ="[ ]";

    Task(int order, boolean marked, String taskName) {
        this.order = order;
        this.isMarked = marked;
        this.taskName = taskName;
    }

    /**
     * Marks a task as complete and shows a brief confirmation message
     */
    public void mark() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        this.isMarked = true;
        this.doneCheckbox = "[X]";
        System.out.println("Woohoo! I've marked this task as done:");
        System.out.print(indent);
        System.out.println(this.doneCheckbox + this.taskName);
        System.out.print(line);
    }
    /**
     * Marks a task as incomplete and shows a brief confirmation message
     */
    public void unmark() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        this.isMarked = false;
        this.doneCheckbox = "[ ]";
        System.out.println("Alright! I've marked this task as incomplete:");
        System.out.print(indent);
        System.out.println(this.doneCheckbox+ " " + this.taskName);
        System.out.print(line);
    }

    /**
     * Set the index of the task within the list
     * @param newOrder The new order to be set
     */
    public void setOrder(int newOrder) {
        this.order = newOrder;
    }

    /**
     * Get the order of a task
     * @return The order of the task
     */
    public int getOrder() {

        return order;
    }
    // Getter method for taskName

    /**
     * Get task name of a task
     * @return Task name
     */
    public String getTaskName() {

        return taskName;
    }

    /**
     * Get the String representation of whether a task is complete or not
     * @return The checkbox showing whether the task is complete
     */
    public String getDoneCheckbox() {
        return doneCheckbox;
    }

    /**
     * Get the completion status of the task
     * @return true if task is marked as complete, false otherwise
     */
    public boolean getIsMarked() {
        return isMarked;
    }

    /**
     * Parent class to be inherited by child classes depending on task type
     * @return type of task
     */
    public String getType() {
        return "";
    }


    public String getEndTime() {
        return "";
    }

    public String getStartTime() {
        return "";
    }

    public String toString() {
        //To be implemented by child classes
        return "";
    }
    public LocalDateTime getDeadline() {
        //To be implemented by Deadline subclass
        return null;
    }
}