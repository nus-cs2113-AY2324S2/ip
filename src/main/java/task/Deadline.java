package task;

import command.JohnException;

/**
 * A type of task containing a set deadline for said task.
 */
public class Deadline extends Task {

    protected String deadline = "Unknown";
    private final int MAX_INPUT_SPLIT = 2;

    /**
     * Constructor for a Deadline task. Function requires the 
     * input string to be in the form of <name> /by <deadline>.
     * Throws an error if blanks or the command parameter is missing.
     * 
     * @param input Input string containing the name and deadline.
     * @throws JohnException Thrown if blanks are present or command is missing.
     */
    public Deadline(String input) throws JohnException {

        if (!input.contains("/by")) {
            throw new JohnException();
        }

        String[] inputSplit = input.split("/by", MAX_INPUT_SPLIT);

        try {

            if (inputSplit[0].trim().isBlank() || inputSplit[1].trim().isBlank()) {
                throw new JohnException();
            }

            super.name = inputSplit[0].trim();
            this.deadline = inputSplit[1].trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Alternate constructor for easy creation of a Deadline task from
     * the condensed string form.
     * 
     * @param name Name of the task.
     * @param deadline Deadline of the task.
     * @param isCompleted Boolean for completion status of the task.
     */
    public Deadline(String name, String deadline, Boolean isCompleted) {
        super(name);
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    /**
     * Override method for printing a deadline task. 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.trim() + ")";
    }

    /**
     * Condenses a deadline task into a single string for easy storage purposes.
     */
    @Override
    public String getDataStorageString() {
        return "d" + DATA_SEPERATOR + this.name + DATA_SEPERATOR + this.deadline + DATA_SEPERATOR + this.isCompleted;
    }

}
