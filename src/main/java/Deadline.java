public class Deadline extends Task{
    protected String by;

    public Deadline(String description) throws QuillException{
        super(description);
        int index = description.indexOf("/by");
        this.description = description.substring(0, index);
        if (this.description.isEmpty()) {
            throw new QuillException();
        }
        this.by = description.substring(index + 3);
        if (by.isEmpty()) {
            throw new EmptyDateException("by");
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
