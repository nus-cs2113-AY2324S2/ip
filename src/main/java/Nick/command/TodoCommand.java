package Nick.command;

import Nick.storage.Storage;
import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.task.Task;
import Nick.task.Todo;

public class TodoCommand extends Command {
    String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(arguments);
        tasks.tasks.add(task);
        ui.printAddTaskMsg(task.toString(), tasks.tasks.size());
        Storage.saveData(tasks.tasks);
    }
}
