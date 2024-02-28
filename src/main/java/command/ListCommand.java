package carrot.command;

import java.util.ArrayList;

import carrot.ui.Ui;
import carrot.task.TaskList;
import carrot.task.Task;

/**
 * Represents a command to list all tasks in the task list.
 * Extends the {@link Command} abstract class.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, printing all tasks in the task list.
     *
     * @param userInput the user input string (not used in this command)
     */
    @Override
    public void execute(String userInput) {
        ArrayList<Task> taskList = TaskList.getTaskList();

        Ui.printListItems(taskList);
    }
}
