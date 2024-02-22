public class Event extends Task{

    private String from;
    private String to;
    public Event(String task,String from,String to)
    {
        super(task);
        this.from=from;
        this.to=to;
    }
    public Event(String task,String from,String to,boolean isComplete)
    {
        super(task,isComplete);
        this.from=from;
        this.to=to;
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: "+from+" to: "+to+")";
    }
    protected String getTaskType() {
        return "E";
    }
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s | %s", getTaskType(), this.isComplete() ? 1 : 0, this.getTask(),this.from,this.to);
    }
}

