package uwunzhe.tasks;

public enum TaskType {
    TODO ("T"),
    DEADLINE ("D"),
    EVENT ("E");

    private final String type;

    /**
     * Constructor for TaskType.
     * 
     * @param type The type of task as a string.
     */
    TaskType(String type) {
        this.type = type;
    }

    /**
     * Gets the type of the task.
     * 
     * @return The type of the task.
     */
    public String getType() {
        return this.type;
    }
}
