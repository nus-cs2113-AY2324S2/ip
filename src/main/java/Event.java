public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description) throws QuillException{
        super(description);
        int fromIndex = description.indexOf("/from");
        this.description = description.substring(0, fromIndex);
        int toIndex = description.indexOf("/to");
        this.from = description.substring(fromIndex + 5, toIndex - 1);
        this.to = description.substring(toIndex + 3);
        if (this.description.isEmpty()) {
            totalTasks--;
            throw new QuillException();
        } else if (this.from.isEmpty()) {
            totalTasks--;
            throw new EmptyDateException("from");
        } else if (this.to.isEmpty()) {
            totalTasks--;
            throw new EmptyDateException("to");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }
}
