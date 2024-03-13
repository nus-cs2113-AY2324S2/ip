package junbot.tasks;

public class Todo extends Task {
    protected String tag;

    /**
     * Constructs a Todo object with the provided description, and associated Tag
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.tag = "T";
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public String toString() {
        return "[" + tag + "]" + "[" + super.getStatusIcon() + "] "
                + description ;
    }

}