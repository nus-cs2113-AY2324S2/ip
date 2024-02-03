public class Deadline extends Task{
    protected String by;

    public Deadline(String description) {
        super(description);
        int index = description.indexOf("/by");
        this.by = description.substring(index + 3);
        this.description = description.substring(0, index);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
