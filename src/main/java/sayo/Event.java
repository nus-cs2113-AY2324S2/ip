package sayo;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + " | " + end;
    }

    public static Task fromFileFormat(String fileFormat) {
        // Assuming fileFormat is in the correct format for Event
        String[] parts = fileFormat.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String start = parts[3].trim();
        String end = parts[4].trim();

        Event event = new Event(description, start, end);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

}
