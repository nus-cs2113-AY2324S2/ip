public class TaskFactory {
    public static Task createTask(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String type = parts[0];
        Task task = null;

        if (type.equals("todo")) {
            task = new Todo(parts[1]);
        } else if (type.startsWith("deadline")) {
            String[] details = parts[1].split(" /by ", 2);
            task = new DeadLine(details[0], details[1]);
        } else if (type.startsWith("event")) {
            String[] details = parts[1].split(" /from ", 2);
            String[] times = details[1].split(" /to ", 2);
            task = new Event(details[0], times[0], times[1]);
        }

        return task;
    }
}
