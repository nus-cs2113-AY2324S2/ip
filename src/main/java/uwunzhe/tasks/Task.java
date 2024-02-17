package uwunzhe.tasks;

public abstract class Task {
    protected String name;
    protected String type;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.type = "";
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     * 
     * @param None
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the status of the task.
     * 
     * @param None
     * @return The status of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets the status of the task.
     * 
     * @param newStatus
     * @return None
     */
    public void setStatus(boolean newStatus) {
        if (newStatus) {
            System.out.println("Good job team! We did it!");
        } else {
            System.out.println("Worryhands! You haven't finished this yet...");
        }

        this.isDone = newStatus;
        System.out.print(" ");
        System.out.println(this);        
    }

    /**
     * Returns the type of the task.
     * 
     * @param None
     * @return The type of the task.
     */
    public String getTaskType() {
        return this.type;
    }

    /**
     * Prints the task and its status in one line.
     * By default, task type is empty.
     * 
     * @param None
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
