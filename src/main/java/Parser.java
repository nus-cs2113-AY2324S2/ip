public class Parser {
    public static Task parseTaskFromString(String line) {
        Task task = null;
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
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
