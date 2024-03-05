// Task.java
package task;

/**
 * Represents a Task abstract class in Kapwa
 * 
 * @see Task
 * @see Todo
 * @see Deadline
 * @see Event
 * 
 * @author yyangda
 * @version 0.1
 * @since 2024-03-03
 * 
 */

public abstract class Task {
    String description;
    String type;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + statusIcon + "] " + description;
    }

    public String toStoreString() {
        String statusIcon = isDone ? "1" : "0";
        return type +  " | " + statusIcon + " | " + this.toString();
    }

    public String getDescription() {
        return description;
    }
}


