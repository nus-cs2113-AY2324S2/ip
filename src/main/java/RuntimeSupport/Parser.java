package RuntimeSupport;
import Event.Deadline;
import Event.Event;
import Event.ToDo;
import Event.Task;
import Event.TaskList;

public class Parser {

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
