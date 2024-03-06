package ChelleCommands;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description) {
        super(parseDescription(description));
        this.from = parseFrom(description);
        this.to = parseTo(description);
    }

    private static String parseDescription(String description) {
        String[] descriptionPart = description.split("/from", 2);
        return descriptionPart[0].trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    private String parseFrom(String description) {
        String[] fromPartStart = description.split("/from", 2);
        String[] fromPartEnd = fromPartStart[1].split("/to", 2);
        return fromPartEnd[0].trim();
    }

    private String parseTo(String description) {
        String[] toPart = description.split("/to", 2);
        return toPart[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + from + " To: " + to + ")";
    }
}
