package Helper;

public class Parser {

    public static final String TASK = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";

    public static Task parseTaskFromString(String line) {
        Task task = null;
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (taskType) {
            case TASK:
                task = new Todo(description);
                break;
            case DEADLINE:
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case EVENT:
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                System.out.println("Unknown task type: " + taskType);
            }
            if (task != null && isDone) {
                task.setAsDone();
            }
        } else {
            System.out.println("Invalid task format: " + line);
        }
        return task;
    }
}
