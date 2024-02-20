public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(from:" + from + " to:" + to + ")";
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description +
                "(from:" + this.from + "to:" + this.to + ")") ;
    }

    @Override
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description +
                "(from:" + this.from + "to:" + this.to + ")") ;
    }
}
