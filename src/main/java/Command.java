import java.util.ArrayList;

/**
 * Command class is an abstract class representing the command to be executed in the Duke application.
 */
public abstract class Command {

    /**
     * Executes the command based on the given TaskList, Ui, and Storage.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if it is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

/**
 * EventCommand class represents a command to add an event task to the task list.
 */
class EventCommand extends Command {
    private String content;

    /**
     * Constructor for EventCommand class.
     *
     * @param content User input content for the event task.
     */
    public EventCommand(String content) {
        this.content = content;
    }

    /**
     * Executes the EventCommand to add an event task to the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = content.split("/from | /to ");
        if (parts.length < 3) {
            throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        ui.line();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + newEvent);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

/**
 * DeleteCommand class represents a command to delete a task from the task list.
 */
class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param taskNumber Index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand to delete a task from the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number!");
        }
        Task deletedTask = tasks.getTasks().get(taskNumber - 1);
        tasks.deleteTask(taskNumber - 1);
        ui.line();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + deletedTask);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

/**
 * ExitCommand class represents a command to exit the Duke application.
 */
class ExitCommand extends Command {

    /**
     * Executes the ExitCommand to exit the Duke application.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Implementation of the execute method for exiting the application
        // ...
    }

    /**
     * Overrides the isExit method to indicate that this command is an exit command.
     *
     * @return true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

/**
 * DeadlineCommand class represents a command to add a deadline task to the task list.
 */
class DeadlineCommand extends Command {
    private final String content;

    /**
     * Constructor for DeadlineCommand class.
     *
     * @param content User input content for the deadline task.
     */
    public DeadlineCommand(String content) {
        this.content = content;
    }

    /**
     * Executes the DeadlineCommand to add a deadline task to the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = content.indexOf("/by");
        if (index != -1) {
            String description = content.substring(0, index).trim();
            String by = content.substring(index + 4).trim();
            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);
            ui.line();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + newDeadline);
            System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        } else {
            throw new DukeException("Please use '/by' to specify the deadline time.");
        }
    }
}

/**
 * ListCommand class represents a command to list all tasks in the task list.
 */
class ListCommand extends Command {

    /**
     * Executes the ListCommand to display all tasks in the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.line();
        ui.greeting();
        int i = 0;
        while (i < tasks.getTasks().size()) {
            Task task = tasks.getTasks().get(i);
            ui.line();
            System.out.println((i + 1) + "." + task);
            i++;
        }
    }
}

/**
 * MarkCommand class represents a command to mark a task as done in the task list.
 */
class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for MarkCommand class.
     *
     * @param taskNumber Index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkCommand to mark a task as done in the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number!");
        }
        tasks.getTasks().get(taskNumber - 1).markAsDone();
        ui.line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" [" + tasks.getTasks().get(taskNumber - 1).getStatusIcon() + "] " + tasks.getTasks().get(taskNumber - 1).description);
    }
}

/**
 * UnmarkCommand class represents a command to unmark a task as done in the task list.
 */
class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for UnmarkCommand class.
     *
     * @param taskNumber Index of the task to be unmarked as done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkCommand to unmark a task as done in the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number!");
        }
        tasks.getTasks().get(taskNumber - 1).unmarkAsDone();
        ui.line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" [" + tasks.getTasks().get(taskNumber - 1).getStatusIcon() + "] " + tasks.getTasks().get(taskNumber - 1).description);
    }
}

/**
 * TodoCommand class represents a command to add a todo task to the task list.
 */
class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for TodoCommand class.
     *
     * @param description User input description for the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the TodoCommand to add a todo task to the task list.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param ui      Ui object for user interface interactions.
     * @param storage Storage object for file storage and retrieval.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPSSSSSSS!!! The task's description is empty! Please do specify!");
        }
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.line();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + newTodo);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> match_input = tasks.find_input(keyword);
        if (match_input.isEmpty()) {
            ui.error("There are no tasks found that match the keyword: " + keyword);
        } else {
            ui.line();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < match_input.size(); i++) {
                System.out.println((i + 1) + "." + match_input.get(i));
            }
            ui.line();
        }
    }
}
