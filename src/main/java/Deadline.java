public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toFileString() {
        return "D|" + (isDone ? 1 : 0) + "|" + description + "|" + by;
    }

    public static Task fromFileString(String line) {
        String[] items = line.split("\\|");
        Deadline deadline = new Deadline(items[2], items[3]);
        if(items[1].equals("1")) {
            deadline.setIsDone();
        }
        return deadline;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
