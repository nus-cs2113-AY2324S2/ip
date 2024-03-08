public class TaskFactory {
    public Task createTask(String type, String description) {
        switch (type) {
            case "T":
                return new Todo(description);
            case "D":
                return new Deadline(description, "");
            case "E":
                return new Event(description, "", "");
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    public Task createTaskWithDateTime(String type, String description, String from, String to) {
        switch (type) {
            case "D":
                return new Deadline(description, from);
            case "E":
                return new Event(description, from, to);
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }
}
