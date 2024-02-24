package bossman.task;

import bossman.ui.Ui;

/**
 * Task is an abstract class representing a task in the task management application.
 * It provides common functionality and properties for different types
 * of tasks: todos, deadline and event.
 */
public abstract class Task {
    protected boolean isMark;
    protected final String DESCRIPTION;
    protected String typeSymbol;

    /**
     * Constructs a Task object with a given description and mark status.
     *
     * @param task the description of the task
     * @param isMark the mark status of the task (true if marked as done, false otherwise)
     */
    public Task(String task, boolean isMark) {
        this.isMark = isMark;
        this.DESCRIPTION = task;
        this.typeSymbol = "";
    }

    /**
     * Prints the task with its mark status and type symbol.
     * The mark status is represented by "[x]" for done tasks and "[ ]" for undone tasks.
     * The type symbol is a specific symbol representing the type of task (e.g., "T" for Todos).
     */
    public void printTask() {
        String markSymbol = isMark ? "[x] " : "[ ] ";
        String taskSymbol = getTypeSymbol();

        Ui.printMessageNoSepSameLine(taskSymbol + markSymbol + getTask());
    }

    /**
     * Sets the mark status of the task to done.
     */
    public void setMark() {
        isMark = true;
    }

    /**
     * Sets the mark status of the task to undone.
     */
    public void setUnmark() {
        isMark = false;
    }

    /**
     * Return the description of the task.
     *
     * @return the description of the task
     */
    public String getTask() {
        return DESCRIPTION;
    }

    /**
     * Return the type symbol of the task.
     *
     * @return the type symbol of the task
     */
    public String getTypeSymbol() {
        return typeSymbol;
    }

    /**
     * Formats the task for saving to file.
     * This method should be overridden by subclasses to provide specific formatting.
     *
     * @return the formatted task string for saving
     */
    public String formatForSave() {
        return "";
    }
}