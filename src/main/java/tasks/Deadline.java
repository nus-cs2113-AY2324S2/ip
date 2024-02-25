package tasks;
import main.DukeException;

public class Deadline extends TaskList {
    protected boolean newInput;

    public Deadline(String description, boolean newInput) throws DukeException {
        super(description);
        setNewInput(newInput);
        toPrint();
    }

    public void setNewInput(boolean newInput) {
        this.newInput = newInput;
    }

    /**
     * Returns the date of deadline.
     * @throws DukeException if number of by in user input is != 2
     */
    public String getBy() throws DukeException{
        String[] splitLine = description.split("/by");
        String[] commandLine = splitLine[0].split("\\s+");

        /* Ensures that user only enters one /by, and all required fields are entered */
        if (commandLine.length < 2 | splitLine.length != 2 | splitLine[1].equals(" ")) {
            throw new DukeException("Invalid Syntax! Please try again!");
        }
        return splitLine[1];
    }

    /* Returns the type of task and their action. */
    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by:" + getBy() + ")";
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
    public void toPrint() throws DukeException {
        if (getBy() != null && newInput) {
                printHeaders();
        }
    }
}