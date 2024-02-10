public class Events extends Task {
    private String from;
    private String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = TaskType.EVENT;
        shortType = this.type.name().substring(0, 1);
    }
    @Override
    public String toString() {
        return "[" + shortType + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
