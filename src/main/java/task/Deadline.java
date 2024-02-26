package task;

import command.JohnException;

public class Deadline extends Task {

    protected String deadline = "Unknown";
    private final int MAX_INPUT_SPLIT = 2;

    public Deadline(String input) throws JohnException {

        if (!input.contains("/by")) {
            throw new JohnException();
        }

        String[] inputSplit = input.split("/by", MAX_INPUT_SPLIT);

        try {

            super.name = inputSplit[0].trim();
            this.deadline = inputSplit[1].trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Input");
        }
    }

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.trim() + ")";
    }

    @Override
    public String getDataStorageString() {
        return "d" + DATA_SEPERATOR + this.name + DATA_SEPERATOR + this.deadline;
    }

}
