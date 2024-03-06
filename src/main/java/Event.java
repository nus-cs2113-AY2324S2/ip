public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event object into a string representation suitable for file storage.
     * The format of the string is "E|isDone|description|from|to", where:
     * - "E" indicates the type of task (Event),
     * - "isDone" is 1 if the event is marked as done, and 0 otherwise,
     * - "description" is the event's description,
     * - "from" is the start date/time of the event,
     * - "to" is the end date/time of the event.
     *
     * @return A string representation of the Event object.
     */
    public String toFileString() {

        return "E|" + (isDone ? 1 : 0) + "|" + description + "|" + from + "|" + to;
    }

    /**
     * Creates an Event object from a string representation that was stored in a file.
     * The expected format of the string is "E|isDone|description|from|to", where each part is separated by a "|".
     * The method parses the string, creates a new Event object with the specified details, and marks it as done if applicable.
     *
     * @param line The string representation of an Event, read from a file.
     * @return An Event object with properties set according to the parsed string.
     */
    public static Task fromFileString(String line) {
        String[] items = line.split("\\|");
        Event event = new Event(items[2], items[3], items[4]);
        if(items[1].equals("1")) {
            event.setIsDone();
        }
        return event;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
