public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return("[E]" + super.toString() + "(from:" + this.from + "to:" + this.to + ")");
    }

    @Override
    public void printTask (int numberOfListItems){
        System.out.println("Got it! I've added this task:\n" + this.toString());
        System.out.println("Now you have " + (numberOfListItems + 1) + " tasks in the list!");
    }
}
