package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.util.Printer;

public class DeleteCommand implements Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
