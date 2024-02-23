package task;

import command.JohnException;

public class Event extends Task {

    protected String from = "Unknown";
    protected String to = "Unknown";
    private final int MAX_INPUT_SPLIT = 2;

    public Event(String input) throws JohnException {

        String[] inputSplit;

        if (!checkValidInput(input)) {
            throw new JohnException();
        }

        try {

            inputSplit = input.split("/from", MAX_INPUT_SPLIT);
            this.name = inputSplit[0].trim();

            inputSplit = inputSplit[1].split("/to", MAX_INPUT_SPLIT);

            this.from = inputSplit[0].trim();
            this.to = inputSplit[1].trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Input");
        }

    }

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    private boolean checkValidInput(String input) {
        return input.contains("/from") && input.contains("/to");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.trim() + " to: " + to.trim() + ")";
    }

    @Override
    public String getDataStorageString() {
        return "e" + DATA_SEPERATOR + this.name + DATA_SEPERATOR + this.from + DATA_SEPERATOR + this.to;
    }

}
