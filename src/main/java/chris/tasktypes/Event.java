package chris.tasktypes;

import chris.customexceptions.illegalEventInput;

public class Event extends Task {
    protected String to;
    protected String from;

    public Event(String[] eventInfo) throws illegalEventInput {
        super(eventInfo[0]);
        if (eventInfo.length != 3) {
            throw new illegalEventInput();
        }
        if (eventInfo[0].trim().isEmpty() | eventInfo[1].trim().isEmpty() | eventInfo[2].trim().isEmpty()) {
            throw new illegalEventInput();
        }
        this.from = eventInfo[1];
        this.to = eventInfo[2];
    }
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + ")" + " (to: " + to + ")";
    }
}
