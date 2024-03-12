public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String res = "[E]" + super.toString();
        res += " (from: " + from + " to: " + to + ")";
        return res;
    }

    @Override
    public String saveTaskFormat() {
        String marked = isDone ? "1" : "0";
        return "E | " + marked + " | " + this.description + " | " + this.from + " | " + this.to + System.lineSeparator();
    }


}
