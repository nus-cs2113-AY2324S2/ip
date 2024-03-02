import Event.Event;
import Event.Deadline;
import Event.ToDo;
import Event.Task;
import Event.TaskList;
import RuntimeSupport.Ui;
import RuntimeSupport.DukeException;
import RuntimeSupport.Storage;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {

            String command = ui.readCommand();
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
                    isExit = true;
                    break;
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
                default:
                    ui.showError("I'm sorry, but I don't know what that means :-(");
                    break;
                }

                if (!isExit) {
                    storage.save(tasks.getTasks());
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                DukeException.handleException(e, command);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}