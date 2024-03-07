package tasktype;

/**
 * Represents the todo task in the JingHao chatbot.
 * Todo class is a subclass of task.
 */
public class Todo extends Task {
    private final static String ICON_TYPE = "[T]";

    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Converts the Todo task to a specific format to display on the screen.
     *
     * @return A formatted string containing the Todo task.
     */
    public String toString(){
        return ICON_TYPE + super.toString();
    }

    /**
     * Converts the Todo task to a specific format to save in the text file.
     *
     * @return The formatted string representing the todo task for saving.
     */
    @Override
    public String toDiskFormat() {
        return "T," + (this.isDone ? "TRUE," : "FALSE,") + description + "\n";
    }
}
