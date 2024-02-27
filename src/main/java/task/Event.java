package task;

import command.JohnException;

/**
 * A type of task that has both a start and end Date/Time string.
 */
public class Event extends Task {

    protected String from = "Unknown";
    protected String to = "Unknown";
    private final int MAX_INPUT_SPLIT = 2;

    /**
     * Constructor for making an Event task.
     * 
     * @param input The user input with name, from and to parameters.
     * @throws JohnException Blank input parameters or missing command arguments.
     */
    public Event(String input) throws JohnException {

        String[] inputSplit;

        if (!checkValidInput(input)) {
            throw new JohnException();
        }

        try {

            inputSplit = input.split("/from", MAX_INPUT_SPLIT);

            if (inputSplit[0].trim().isBlank()) {
                throw new JohnException();
            }

            this.name = inputSplit[0].trim();

            inputSplit = inputSplit[1].split("/to", MAX_INPUT_SPLIT);

            if (inputSplit[0].trim().isBlank()) {
                throw new JohnException();
            }

            this.from = inputSplit[0].trim();

            if (inputSplit[1].trim().isBlank()) {
                throw new JohnException();
            }

            this.to = inputSplit[1].trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Input");
        }

    }

    /**
     * Constructor made for the purposes of reading in a condensed string
     * for easy recreation of the Event task.
     * 
     * @param name Name of the Event task.
     * @param from Start date/time of the Event task.
     * @param to End date/time of the Event task.
     * @param isCompleted Completion status of the Event task.
     */
    public Event(String name, String from, String to, Boolean isCompleted) {
        super(name);
        this.from = from;
        this.to = to;
        this.isCompleted = isCompleted;
    }

    /**
     * Check if the command parameters /from and /to are present 
     * in a user given string.
     * 
     * @param input Userinput to check.
     * @return True if input is valid, false otherwise.
     */
    private boolean checkValidInput(String input) {
        return input.contains("/from") && input.contains("/to");
    }

    /**
     * Override method for printing an Event task as a string. 
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.trim() + " to: " + to.trim() + ")";
    }

    /**
     * Condensed string storing all relevant data of an Event task. 
     * For Data storage purposes.
     */
    @Override
    public String getDataStorageString() {
        return "e" + DATA_SEPERATOR + this.name + DATA_SEPERATOR + this.from + DATA_SEPERATOR + this.to + DATA_SEPERATOR + this.isCompleted;
    }

}
