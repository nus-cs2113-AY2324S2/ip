public class Task {
    private final String description;
    private final String type;
    private final boolean status;

    /**
     * Constructor for Task class
     *
     * @param description description of the task
     * @param type type of the task
     */
    Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.status = false;
    }

    /**
     * Constructor for Task class
     *
     * @param description description of the task
     * @param type type of the task
     * @param status status of the task
     */
    Task(String description, String type, boolean status) {
        this.description = description;
        this.type = type;
        this.status = status;
    }

    /**
     * Constructor for Task class
     * Creates a new Task based on current attributes in the class
     *
     * @param a Task class
     * @param status status of the task
     */
    Task(Task a, boolean status){
        this.description = a.description;
        this.type = a.type;
        this.status = status;
    }

    /**
     * Returns new marked task
     *
     * @return new task that is marked
     */
    Task markTask() {
        return new Task(this, true);
    }

    /**
     * Returns new unmarked task
     *
     * @return new task that is unmarked
     */
    Task unmarkTask() {
        return new Task(this,false);
    }

    /**
     * Getter for description
     * @return description attribute
     */
    String getDescription() {
        return this.description;
    }

    /**
     * Getter for type
     * @return type attribute
     */
    String getType() {
        return this.type;
    }

    /**
     * Getter for status
     * @return status attribute
     */
    boolean getStatus() {
        return this.status;
    }

    /**
     * Encodes task in a suitable format to be saved in txt file
     *
     * @return encoded string
     */
    String encodeString() {
        String type = this.type;
        String status = this.status ? "X" : " ";
        String description = this.description;
        return String.format(
                "%s|%s|%s",
                type,
                status,
                description);
    }

    /**
     * Overrides Object to String method for more verbose output
     *
     * @return overriden string
     */
    @Override
    public String toString() {
        String type = this.type;
        String status = this.status ? "X" : " ";
        String description = this.description;
        return String.format(
                "%s|%s|%s",
                type,
                status,
                description);
    }
}
