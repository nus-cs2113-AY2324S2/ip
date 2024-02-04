public class Event extends Deadline{

    protected String before;

    public Event(String description, String before, String by) {
        super(description, by);
        this.before = before;
    }

}
