public class ToDo extends Task {
   // public static String taskType = "T";

    public ToDo(String userInput) {
        super(userInput);
    }

    @Override
    public String taskString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}

