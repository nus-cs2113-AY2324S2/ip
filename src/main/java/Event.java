public class Event extends Task {
        protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void printTask() {
        System.out.println (getTypeIcon() + getStatusIcon()+ " " + this.description + " (from: " + this.from + " to: "+ this.to + ")");
    }

    @Override
    public String getTypeIcon(){
        return "[E]";
    }



}
