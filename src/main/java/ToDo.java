public class ToDo extends Task {
   // public static String taskType = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String taskString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}

