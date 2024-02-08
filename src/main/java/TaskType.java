public enum TaskType {
    TODO ("T"),
    DEADLINE ("D"),
    EVENT ("E");

    private final String type;

    /**
     * Constructor for TaskType.
     * 
     * @param type The type of task.
     */
    TaskType(String type) {
        this.type = type;
    }
}
