public class ToDo extends Task {
    private static final String TYPE = "T";

    /**
     * Constructor for ToDo task
     *
     * @param description description of the task
     */
    ToDo(String description) {
        super(description, TYPE);
    }

    /**
     * Constructor for Event task
     *
     * @param description description of the task
     * @param status status of the task
     */
    ToDo(String description, boolean status) {
        super(description, TYPE, status);
    }
}
