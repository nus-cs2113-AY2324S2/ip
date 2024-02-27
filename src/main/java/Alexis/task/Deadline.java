package Alexis.task;

import Alexis.console.Ui;

/**
 * The Deadline class represents a deadline task with a specific due date or time.
 */
public class Deadline extends Task {
    protected String by;

    public String getBy() {
        return by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a Deadline object from a user input string.
     * The input must contain a description and a deadline separated by keyword "/by".
     *
     * @param input User input string for the Deadline object.
     * @return Deadline object or null if the input format is incorrect.
     * @throws IndexOutOfBoundsException If keyword "/by" is missing.
     */
    protected static Deadline getDeadline(String input) {
        String keyword = "/by";
        int keywordIndex = input.indexOf(keyword);
        try {
            String description = input.substring(0, keywordIndex).trim();
            String taskDeadline = input.substring(keywordIndex + keyword.length()).trim();
            return new Deadline(description, taskDeadline);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.MISSING_DEADLINE_ERROR);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return A string representation of the task with symbol "D" denoting task type.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n", super.toString(), this.by);
    }
}

