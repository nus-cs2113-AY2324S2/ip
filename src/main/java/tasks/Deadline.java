package tasks;
import main.DukeException;

import static main.Ui.printHeaders;


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
            System.out.println(String.valueOf(e.getMessage()));
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