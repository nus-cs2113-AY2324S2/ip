package carrot.command;

import java.util.ArrayList;

import carrot.ui.Ui;
import carrot.task.TaskList;
import carrot.task.Task;

public class ListCommand extends Command {
    @Override
    public void execute(String userInput) {
        ArrayList<Task> taskList = TaskList.getTaskList();

        Ui.printListItems(taskList);
    }
}
