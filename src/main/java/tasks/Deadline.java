// Subclass of Task

package tasks;
import main.DukeException;

public class Deadline extends Task {

    public Deadline(String description) throws DukeException {
        super(description); // Automatically invokes the constructor of Task
        toPrint();
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
            printHeaders();
            System.out.println(this);
        }
    }
}
