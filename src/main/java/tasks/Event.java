// subclass of Task
package tasks;
import main.DukeException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
        toPrint();
    }

    @Override
    public String toString() {
        String date;
        try {
            String[] splitLine = description.split("/from");
            String[] durationLine = splitLine[1].split("/to");
            if (splitLine.length != 2 || durationLine.length != 2) {
                throw new DukeException("Invalid Syntax! Please try again!");
            }
            date = " (from:" + durationLine[0]  + "to:" + durationLine[1] + ")";

        return "[E]" + super.toString() + date;
    } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
    public void toPrint() {
        if (toString() != null) {
            printHeaders();
            System.out.println(this);
        }
    }
}

