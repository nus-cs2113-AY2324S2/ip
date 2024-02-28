package uwunzhe.tasks;

import uwunzhe.util.Ui;

public abstract class Task {
    protected String name;
    protected String type;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param name The name of the task as a string.
     */
    public Task(String name) {
        this.name = name;
        this.type = "";
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     * 
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the status of the task.
     * 
     * @return Task status.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets the status of the task.
     * 
     * @param newStatus The new status of the task.
     */
    public void setStatus(boolean newStatus) {
        if (newStatus) {
            Ui.println("Good job team! We did it!");
        } else {
            Ui.println("Worryhands! You haven't finished this yet...");
        }

        this.isDone = newStatus;
        Ui.print(" ");
        Ui.printlnTask(this);        
    }

    /**
     * Returns the type of the task.
     * 
     * @return The type of the task.
     */
    public String getTaskType() {
        return this.type;
    }

    /**
     * Prints the task and its status in one line.
     * By default, task type is empty.
     * 
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String completion = this.isDone ? "X" : " ";
        return String.format("[%s] %s",
                completion, this.name);
    }

    public abstract String toStorageString(String delimiter);
}
