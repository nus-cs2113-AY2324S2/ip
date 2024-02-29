package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.util.Printer;

/**
 * Command indicating a delete command, implements the Command interface
 */
public class DeleteCommand implements Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command and deletes a task accordingly
     *
     * @param taskManager the TaskManager instance used by Joe
     */
    @Override
    public void executeCommand(TaskManager taskManager) {
        try {
            taskManager.deleteTask(taskNumber);
        } catch (JoeException e) {
            Printer.printDeleteError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
