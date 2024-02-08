public class Event extends Task{
    protected String timeRange;

    public Event(String description, String timeRange){
        super(description + timeRange);
        this.type = 'E';
    }

    @Override
    public char getType() {
        return this.type;
    }
}
