package duke.task;

/**
 * Represents the Deadline task of the Duke chatbot.
 * Deadline tasks are tasks with a specific deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new Deadline object with the specified description and deadline.
     *
     * @param description Description of the Deadline task
     * @param by Deadline of the Deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Deadline task for saving.
     */
    @Override
    public String toDisk() {
        return "D" + super.toDisk() + " | " + this.by + System.lineSeparator();
    }

    /**
     * Converts the Deadline task to the specific format for displaying on the screen.
     *
     * @return A formatted string containing the Deadline task for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
