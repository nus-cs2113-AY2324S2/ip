public class Events extends Task {
    protected String dueFrom;
    protected String dueAt;
    public Events(String description, String dueFrom, String dueAt) {
        super(description);
        this.dueFrom = dueFrom;
        this.dueAt = dueAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + dueFrom + "to:" + dueAt + ")";
    }
}
