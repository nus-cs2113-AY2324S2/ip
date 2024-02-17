// Subclass of Task

package tasks;
import main.DukeException;

public class Deadline extends Task {

    protected boolean newInput;

    public Deadline(String description, boolean newInput) throws DukeException {
        super(description, newInput); // Automatically invokes the constructor of Task
        setNewInput(newInput);
        toPrint();
    }

    public void setNewInput(boolean newInput) {
        this.newInput = newInput;
    }
    public String getBy() throws DukeException{
        String[] splitLine = description.split("/by");
        if (splitLine.length != 2) {
            throw new DukeException("Invalid Syntax! Please try again!");
        }
        return splitLine[1];
    }

    @Override
    public String toString() {

        try {
            return "[D]" + super.toString() + " (by:" + getBy() + ")";
        } catch (DukeException e) {
            System.out.println("error!, please try again!");
            throw new RuntimeException();
        }
    }
    public void toPrint() throws DukeException {
        if (getBy() != null) {
            if (newInput) {
                printHeaders();
            }
        }
    }
}