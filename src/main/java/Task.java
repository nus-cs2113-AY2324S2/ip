public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param name The name of the task.
     * @return None
     */
    public Task(String name) {
        this.name = name;
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
        String completion = newStatus ? "X" : " ";
        if (newStatus) {
            System.out.println("Good job team! You did it!");
        } else {
            System.out.println("Worryhands! you haven't finished this yet...");
        }

        System.out.println(" [" + completion + "] " + this.name);
        this.isDone = newStatus;
    }
}
