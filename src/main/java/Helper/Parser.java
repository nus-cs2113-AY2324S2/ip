package Helper;

public class Parser {

    private static final int PART_0 = 0;
    private static final int PART_1 = 1;
    private static final int PART_2 = 2;
    private static final int PART_3 = 3;
    private static final int PART_4 = 4;
    private static final String DONE = "1";
    public static final String TASK = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";

    public static Task parseTaskFromString(String line) {
        Task task = null;
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String taskType = parts[PART_0];
            boolean isDone = parts[PART_1].equals(DONE);
            String description = parts[PART_2];
            switch (taskType) {
            case TASK:
                task = new Todo(description);
                break;
            case DEADLINE:
                String by = parts[PART_3];
                task = new Deadline(description, by);
                break;
            case EVENT:
                String from = parts[PART_3];
                String to = parts[PART_4];
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
