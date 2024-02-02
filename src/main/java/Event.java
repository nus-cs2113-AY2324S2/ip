public class Event extends Task {
    protected String from;
    protected String to;
    public Event (String taskName, String from, String to) {
        super("E", taskName);
        this.from = from;
        this.to = to;
    }

    public String printTask() {
        return super.printTask() + "(from: " + this.from + "to: " + this.to +  ")";
    }
}
