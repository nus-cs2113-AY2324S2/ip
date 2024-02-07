public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event extractEvent (String input) {
        Event eventDetails  = new Event("random","random", "random");
        //splits string according to /by keyword
        String[] parts = input.split("/from|/to");
        //extracts task portion from input, after deadline keyword
        eventDetails.description = parts[0].substring("event".length()).trim();
        //extracts from and to portion from input
        eventDetails.from = parts[1].trim();
        eventDetails.to = parts[2].trim();
        return eventDetails;
    }
}
