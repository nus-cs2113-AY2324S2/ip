package tasks;
import main.DukeException;
import static main.Ui.printHeaders;

public class Event extends TaskList {

    protected boolean newInput;
    public Event(String description, boolean newInput) {
        super(description);
        setNewInput(newInput);
        toPrint();
    }
    public void setNewInput(boolean newInput) {
        this.newInput = newInput;
    }
    @Override
    public String toString() {
        String date;
        try {
            String[] splitLine = description.split("/from");
            String[] commandLine = splitLine[0].split(" ");
            String[] durationLine = splitLine[1].split("/to");
            if (commandLine.length < 2 || splitLine.length != 2 || durationLine.length != 2 || durationLine[0].equals("  ") || durationLine[1].equals(" ")) {
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
            if (newInput) {
                printHeaders();
            }
        }
    }
}