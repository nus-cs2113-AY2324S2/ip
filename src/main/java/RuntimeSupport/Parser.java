package RuntimeSupport;
import Event.Deadline;
import Event.Event;
import Event.ToDo;
import Event.Task;
import Event.TaskList;

/**
 * The <code>Parser</code> class parses and executes commands input by the
 * user.
 * This class is responsible for interpreting the user's input and performing
 * the corresponding actions, such as adding, deleting, or marking tasks as
 * done or not done. It also handles saving the current state of tasks after
 * each command execution.
 */
public class Parser {

    /**
     * Executes the user command on the given TaskList, updates the UI and storage
     * accordingly.
     * This method interprets the command, performs the necessary action on the
     * TaskList, updates the UI to reflect changes, and ensures the current state
     * of the TaskList is saved.
     *
     * @param command The user input command to be executed.
     * @param tasks The list of tasks to be manipulated based on the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage mechanism to save the state of tasks.
     * @return true if the command is "bye", indicating the program should exit;
     * false otherwise.
     */
    public static boolean execution(String command, TaskList tasks, Ui ui, Storage storage) {
        try {
            String commandWord = command.split(" ")[0];
            int index;
            ui.showLine();
            switch (commandWord) {
            case "list":
                ui.showTaskList(tasks);
                break;
            case "bye":
                ui.showGoodbye();
                return true;
            case "mark":
                index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.markTaskAsDone(index);
                ui.markTaskDone(tasks.getTask(index));
                break;
            case "unmark":
                index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.markTaskAsNotDone(index);
                ui.markTaskUndone(tasks.getTask(index));
                break;
            case "todo":
                Task todo = new ToDo(command);
                tasks.addTask(todo);
                ui.showTaskAdded(todo, tasks.getSize());
                break;
            case "deadline":
                Task deadline = new Deadline(command);
                tasks.addTask(deadline);
                ui.showTaskAdded(deadline, tasks.getSize());
                break;
            case "event":
                Task event = new Event(command);
                tasks.addTask(event);
                ui.showTaskAdded(event, tasks.getSize());
                break;
            case "delete":
                index = Integer.parseInt(command.split(" ")[1]) - 1;
                Task removedTask = tasks.removeTask(index);
                ui.showTaskDeleted(removedTask, tasks.getSize());
                break;
            case "find":
                String keyword = command.substring(command.indexOf(" ") + 1);
                ui.showFoundResults(tasks.findTasks(keyword));
                break;
            default:
                ui.showError("I'm sorry, but I don't know what that means :-(");
                break;
            }
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            DukeException.handleException(e, command);
        }
        return false;
    }
}
