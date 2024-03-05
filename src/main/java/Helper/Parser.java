package Helper;

/**
 * The Parser class is responsible for parsing tasks fetched from the file.
 * It handles saved tasks and creates appropriate task objects based on the identifier.
 */

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
    public static final int PARTS_LENGTH = 3;

    /**
     * Handles the task stored in the file by parsing it and creating appropriate task objects.
     *
     * @param line The task being processed.
     * @return task The task being returned with appropriate details parsed from the file
     */

    public static Task parseTaskFromString(String line) {
        Task task = null;
        String[] parts = line.split(" \\| ");
        if (parts.length >= PARTS_LENGTH) {
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
