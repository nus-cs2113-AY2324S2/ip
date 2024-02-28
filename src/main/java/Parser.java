import tasks.Task;

import exceptions.WallybotException;
import exceptions.InvalidCommandException;


/**
 * Makes sense of the user's input.
 */
public class Parser {
    /**
     * Execute given command
     */
    public void executeCommand(String command, String input, Tasklist tasks) throws WallybotException {
        switch (command) {

        case "todo":
        case "deadline":
        case "event":
            createTask(command, input, tasks);
            break;

        case "mark":
        case "unmark":
            // Mark or unmark task
            tasks.markDone(command.equals("mark"), Integer.parseInt(input));
            break;

        case "delete":
            // Delete a task
            tasks.deleteTask(Integer.parseInt(input));
            break;

        case "list":
            // View all tasks
            tasks.viewTasks();
            break;

        case "bye":
            // Exit chatbot
            Wallybot.exitWally();
            break;

        default:
            // Invalid command
            throw new InvalidCommandException();
        }
    }

    /**
     * Create subclass Todo/Deadline/Event of Task
     */
    public void createTask(String command, String input, Tasklist tasks) throws WallybotException {
        switch (command) {
        case "todo":
            // Add Todo
            Task todo = tasks.createTodo(input);
            tasks.addTask(todo);
            break;

        case "deadline":
            // Add Deadline
            Task deadline = tasks.createDeadline(input);
            tasks.addTask(deadline);
            break;

        case "event":
            // Add Event
            Task event = tasks.createEvent(input);
            tasks.addTask(event);
            break;
        }
    }

}
