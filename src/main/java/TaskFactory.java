public class TaskFactory {
    public static Task createTask(String userInput) throws HandleException {
        String[] parts = userInput.split(" ", 2);
        String type = parts[0];
        Task task;

        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new HandleException("OOPS!!! The description of a task cannot be empty.");
        }

        // Declare details outside the switch to use across multiple cases
        String[] details = parts[1].split(" /by ", 2);

        switch (type) {
        case "todo":
            task = new Todo(parts[1]);
            break;
        case "deadline":
            if (details.length < 2 || details[1].isEmpty()) {
                throw new HandleException("OOPS!!! The date of a deadline cannot be empty.");
            }
            task = new DeadLine(details[0], details[1]);
            break;
        case "event":
            // Assuming event command format was intended to be "event description /from start /to end"
            details = parts[1].split(" /from ", 2); // Re-split for event
            if (details.length < 2 || details[1].isEmpty()) {
                throw new HandleException("OOPS!!! The start time of an event cannot be empty.");
            }
            String[] times = details[1].split(" /to ", 2);
            if (times.length < 2 || times[1].isEmpty()) {
                throw new HandleException("OOPS!!! The end time of an event cannot be empty.");
            }
            task = new Event(details[0], times[0], times[1]);
            break;
        default:
            throw new HandleException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return task;
    }
}

