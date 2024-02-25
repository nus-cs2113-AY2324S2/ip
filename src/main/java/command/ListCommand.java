package command;

import storage.Storage;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks in the list of JingHao chatbot.
 */
public class ListCommand implements Command {

    /**
     * Executes the command by iterating through the task list and displaying each task.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.printMessage("You have no task.");
        } else {
            ui.printWithoutDivider("Here are the tasks in your list:");
            int i = 1;
            for (Task item: taskList) {
                System.out.println( i + "." + item );
                i++;
            }
            ui.printDivider();
        }
    }

    /**
     * Determines whether the command is an exit command.
     *
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
