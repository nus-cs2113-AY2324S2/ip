package Alexis.task;

import Alexis.console.Ui;

public class Deadline extends Task {
    protected String by;

    public String getBy() {
        return by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

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

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n", super.toString(), this.by);
    }
}

