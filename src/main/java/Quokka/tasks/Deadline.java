package Quokka.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline parseFromString(String data, boolean isDone) {
        String[] parts = data.split("\\(by: ", 2);
        if (parts.length == 2) {
            String description = parts[0];
            String by = parts[1].substring(0, parts[1].length() - 1);
            return new Deadline(description, by, isDone);
        }
        return null;
    }



}
