package Events;

import javax.swing.table.TableRowSorter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isTodo;
    protected boolean isEvent;
    protected boolean isDDL;

    /**
     * Constructs a new Task instance with the specified description. Initializes the task as not done and not of any specific type (ToDo, Event, Deadline).
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isTodo = false;
        this.isEvent = false;
        this.isDDL = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    };


    /**
     * Marks the task as a ToDo.
     */
    public void markAsToDo() {
        isTodo = true;
    };

    /**
     * Marks the task as a Deadline.
     */
    public void markAsDDL() {
        isDDL = true;
    };

    /**
     * Marks the task as an Event.
     */
    public void markAsEvent() {
        isEvent = true;
    };

    /**
     * Unmarks the task as completed.
     */
    public void unmarkAsDone() {
        isDone = false;
    }


    /**
     * Returns a symbol representing the completion status of the task.
     *
     * @return "X" if the task is completed, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a symbol representing the type of the task.
     *
     * @return "D" for Deadlines, "T" for ToDos, "E" for Events, otherwise a space character.
     */
    public String getTypeIcon() { return isDDL? "D" :( isTodo ? "T" :( isEvent ? "E" : " "));};

    /**
     * Returns a string representation of the task, currently just its description.
     *
     * @return The task's description.
     */
    public String toString() {
        return description;
    }

    /**
     * Returns the task's description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Formats the task for file storage.
     *
     * @return A formatted string suitable for file storage.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}