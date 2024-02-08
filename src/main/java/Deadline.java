public class Deadline extends Task {

    public String deadline;
    //public static String taskType = "D";

    public Deadline(String description, String date) {
        super(description);
        deadline = date;
    }

    @Override
    public String getDescription() {
        return description + " (by: " + deadline + ")";
    }

    @Override
    public String taskString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
