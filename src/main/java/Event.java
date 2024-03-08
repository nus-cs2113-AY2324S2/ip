//Event class to represent tasks with a specific start and end date/time
public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "E";
    }

    @Override
    public String toFileString() {
        super.toFileString();
        String status = isDone ? "1" : "0";
        return String.format("%s | %s | %s | %s | %s", getTypeSymbol(), status, description, from, to );
    }

    public static Event fromFileFormat(String fileLine) {
        String[] parts = fileLine.split(" \\| ");

        if (parts.length < 5) {
            // Not enough data to create a valid Event task
            return null;
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String from = parts[3];
        String to = parts[4];

        Event eventTask = new Event(description, from, to);
        eventTask.setDone(isDone);

        return eventTask;
    }

}
