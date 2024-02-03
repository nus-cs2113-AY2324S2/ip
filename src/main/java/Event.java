
// subclass of Task
public class Event extends Task{
    protected String from;
    protected String to;

    String[] splitLine = description.split("/"); // Split input from / onwards

    public Event(String description, int index) {
        super(description, index);
        System.out.println(toString());
    }
    public String getFrom() {
        return splitLine[1].substring(5); // Return starting time
    }

    public String getTo() {
        return splitLine[2].substring(3); // Return ending time
    }

    // Override task's toString() to add [E] and the deadline interval
    @Override
    public String toString() {
        String date = " (from: " + getFrom()  + "to: " + getTo() + ")";
        return "[E]" + super.toString() + date;
    }
}
