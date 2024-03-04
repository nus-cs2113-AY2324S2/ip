package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Todo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        System.out.println("Got it. I've added this task: \n" + todo);
        ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list~");
    }
}
