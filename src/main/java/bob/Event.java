package bob;

import static bob.Bob.EVENT_ICON;

public class Event extends Deadline {

    protected String start;

    public Event(String description, String start, String by, boolean isDone) {
        super(description, by, isDone);
        this.start = start;
    }

    public Event(String stringRepresentation) {
        super(stringRepresentation);
        String[] split = stringRepresentation.substring(7).split(" \\(from: ");
        this.description = split[0];
        String[] split2 = split[1].split(" to: ");
        this.start = split2[0];
        this.by = split2[1].substring(0, split2[1].length() - 1);
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.getListItem() + " (from: " + start + " to: " + by + ")";
    }

}
