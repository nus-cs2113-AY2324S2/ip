package tasks;

public class Event extends Task{
    protected String timeRange;

    public Event(String description, String timeRange, int taskNum){
        super(description + timeRange, taskNum);
        this.type = 'E';
    }

    @Override
    public char getType() {
        return this.type;
    }
}
