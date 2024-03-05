/**
 * TaskFactory is responsible for creating Task objects based on user input.
 * It interprets the command and parameters provided by the user to instantiate
 * the appropriate Task subclass (Todo, DeadLine, or Event).
 */
public class TaskFactory {

    /**
     * Creates a Task object based on the user's input.
     * This method parses the user input to determine the type of task to create
     * and extracts any necessary parameters (e.g., description, due date).
     *
     * @param userInput The complete command input from the user, including the task type
     *                  and any parameters.
     * @return A Task object corresponding to the user's command.
     * @throws HandleException If the input format is incorrect or insufficient information
     *                         is provided to create a task.
     */
    public static Task createTask(String userInput) throws HandleException {
        String[] parts = userInput.split(" ", 2);
        String type = parts[0];
        Task task;

        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new HandleException("OOPS!!! The description of a task cannot be empty.");
        }

        // Details are declared outside the switch to use across multiple cases.
        String[] details;

        switch (type.toLowerCase()) {
        case "todo":
            task = new Todo(parts[1]);
            break;
        case "deadline":
            details = parts[1].split(" /by ", 2);
            if (details.length < 2 || details[1].isEmpty()) {
                throw new HandleException("OOPS!!! The date of a deadline cannot be empty.");
            }
            task = new DeadLine(details[0], details[1]);
            break;
        case "event":
            details = parts[1].split(" /from ", 2);
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

